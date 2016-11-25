package com.lntormin.javaee.web.rest.services;

import com.lntormin.javaee.ejb.remote.PositionBeanRemote;
import com.lntormin.javaee.ejb.remote.UserBeanRemote;
import com.lntormin.javaee.ejb.entities.Position;
import com.lntormin.javaee.ejb.entities.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author lntormin
 */
@Path("/application/")
public class ApplicationRestService {

    private static final String APIKEY = "9ae5c80a3a9a09f12c21939d9b11b02c";
    private static final String PRIVATEKEY = "e418f4b3cf47afb37064e4cedf283c354ccf2e25";
    private static final String BASEURL = "http://gateway.marvel.com/v1/public/characters";

    private PositionBeanRemote positionBeanRemote;
    private UserBeanRemote userBeanRemote;

    @Path("/newuser")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void newUser(String userXml) {
        System.out.println(userXml);
        try {
            Context context = new InitialContext();
            userBeanRemote = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
        } catch (NamingException ex) {
            Logger.getLogger(ApplicationRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(User.class);
            Unmarshaller u = jaxbc.createUnmarshaller();
            StringReader reader = new StringReader(userXml);
            User user = (User) u.unmarshal(reader);
            userBeanRemote.createUser(user);
        } catch (JAXBException jaxbe) {
            Logger.getLogger(ApplicationRestService.class.getName()).log(Level.SEVERE, null, jaxbe);
        }
    }

    @Path("/user/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> searchUserByUsername(@PathParam("username") final String username) {
        try {
            Context context = new InitialContext();
            userBeanRemote = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
        } catch (NamingException exception) {
            Logger.getLogger(ApplicationRestService.class.getName()).log(Level.SEVERE, null, exception);
        }

        return userBeanRemote.searchUserByUsername(username);
    }

    @Path("/newposition")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void newPosition(String positionXml) {
        System.out.println(positionXml);
        Logger.getLogger(ApplicationRestService.class.getName()).log(Level.INFO, positionXml);
        Context context = null;
        positionBeanRemote = null;
        try {
            context = new InitialContext();
            positionBeanRemote = (PositionBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/PositionBean");
        } catch (NamingException ex) {
            Logger.getLogger(ApplicationRestService.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            JAXBContext jaxbc = JAXBContext.newInstance(Position.class);
            Unmarshaller u = jaxbc.createUnmarshaller();
            StringReader reader = new StringReader(positionXml);

            Position position = (Position) u.unmarshal(reader);
            positionBeanRemote.save(position);
        } catch (JAXBException jaxbe) {
            Logger.getLogger(ApplicationRestService.class.getName()).log(Level.SEVERE, null, jaxbe);
        }
    }

    @GET
    @Path("/marvel/{character}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMarvelJSON(@PathParam("character")final String character) {
        //Criação de um timestamp
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String ts = sdf.format(date);

        //Criação do HASH
        String hashStr = MD5(ts + PRIVATEKEY + APIKEY);
        String uri;
        
        String parsedCharacter = character.replaceAll(" ", "%20");
        //url de consulta
        uri = BASEURL + "?nameStartsWith=" + parsedCharacter + "&ts=" + ts + "&apikey=" + APIKEY + "&hash=" + hashStr;
        StringBuilder out = new StringBuilder();
        System.out.println(uri);
        try {
            HttpClient cliente = HttpClients.createDefault();

            //HttpHost proxy = new HttpHost("172.16.0.10", 3128, "http");
            //RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            HttpGet httpget = new HttpGet(uri);
            //httpget.setConfig(config);
            HttpResponse response = cliente.execute(httpget);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            Header[] h = response.getAllHeaders();

            for (Header head : h) {
                System.out.println(head.getValue());
            }

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream instream = entity.getContent(); BufferedReader reader = new BufferedReader(new InputStreamReader(instream))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                    
                }
            }
        } catch (IOException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @GET
    @Path("/positions/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Position> listPositions(@PathParam("login") final String login) {
        try {
            Context context = new InitialContext();
            positionBeanRemote = (PositionBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/PositionBean");
        } catch (NamingException ex) {
            Logger.getLogger(ApplicationRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return positionBeanRemote.list(login);
    }
}
