package com.lntormin.javaee.web.rest.services;

import com.lntormin.javaee.ejb.beans.PositionBeanRemote;
import com.lntormin.javaee.ejb.beans.UserBeanRemote;
import com.lntormin.javaee.ejb.entities.Position;
import com.lntormin.javaee.ejb.entities.User;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

/**
 *
 * @author lntormin
 */
@Path("/application/")
@Stateless
@LocalBean
public class ApplicationRestService {

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

    @Path("/newposition")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void newPosition(String positionXml) {
        System.out.println(positionXml);
        try {
            Context context = new InitialContext();
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
    @Path("/positions/{login}")
    @Produces({"application/xml"})
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
