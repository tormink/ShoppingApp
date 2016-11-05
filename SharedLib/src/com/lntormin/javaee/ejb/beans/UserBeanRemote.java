/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.ejb.beans;

import com.lntormin.javaee.ejb.entities.User;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lntormin
 */
@Remote
public interface UserBeanRemote {
    
    public User createUser(User user);
    
    public List<User> list();
    
    public User searchUserById(final int id);
    
    public Collection searchUserByName(final String name);
    
    public void removeUser(final int id);
    
    public void updateUser(User user);
    
    public boolean authenticate(String user, String password);
    
    public User changePassword(String user, String password, String newPassword);
    
    public String getHash(String password);
}
