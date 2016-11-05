package com.lntormin.javaee.ejb.beans;

import com.lntormin.javaee.ejb.entities.User;
import com.lntormin.javaee.ejb.interceptors.LogInterceptor;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lntormin on 03/10/16.
 */

@Stateless
@Interceptors(LogInterceptor.class)
public class UserBean implements UserBeanRemote{

    @PersistenceContext(unitName = "DerbyPU")
    private EntityManager em;

    private String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();
        System.out.println("Salt: " + salt.length);

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();

        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private boolean validatePassword(String candidatePassword, String passwordHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = passwordHash.split(":");

        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(candidatePassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] candidateHash = secretKeyFactory.generateSecret(spec).getEncoded();
        int diff = hash.length ^ candidateHash.length;

        for (int i = 0; i < hash.length && i < candidateHash.length; i++) diff |= hash[i] ^ candidateHash[i];

        return diff == 0;
    }

    private String getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt.toString();
    }

    private String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    @Override
    public User createUser(User user) {
        em.persist(user);
        em.flush();
        em.refresh(user);
        return user;
    }

    @Override
    public List<User> list() {
        Query query = em.createQuery("FROM User user");
        return query.getResultList();
    }

    @Override
    public User searchUserById(final int id) {
        return em.find(User.class, id);

    }

    @Override
    public Collection searchUserByName(final String name) {
        Query q = em.createQuery("select u from User u where u.name= :par1");
        q.setParameter("par1", name);
        return q.getResultList();
    }

    @Override
    public void removeUser(final int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public void updateUser(User user) {
        User updateUser = em.find(User.class, user.getId());
        if (updateUser != null) {
            updateUser.setName(user.getName());
            updateUser.setSurname(user.getSurname());
            updateUser.setUsername(user.getUsername());
            em.merge(updateUser);
        }
    }

    @Override
    public User changePassword(String user, String password, String newPassword) {
        Query query = em.createQuery("FROM User u where u.username='" + user + "'");
        List<User> users = query.getResultList();
        if (users.size() != 1) return null;

        User u = users.get(0);

        try {
            if (user.equals(u.getUsername()) && validatePassword(password, u.getHash())) {
                u.setHash(generateStrongPasswordHash(newPassword));
                em.persist(u);
                return u;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean authenticate(String user, String password) {
        Query query = em.createQuery("FROM User u where u.username='" + user + "'");
        List<User> users = query.getResultList();
        if (users.size() != 1) {
            return false;
        }

        User u = users.get(0);
        try {
            if (user.equals(u.getUsername()) && validatePassword(password, u.getHash())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getHash(String password) {
        try {
            return generateStrongPasswordHash(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
