package com.lntormin.javaee.web.rest.services;

import com.lntormin.javaee.ejb.beans.UserBeanRemote;
import com.lntormin.javaee.ejb.entities.User;
import java.io.StringReader;
import java.net.URI;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author lntormin
 */
@Path("/user/")
public class UserRestService {

    public UserRestService() {
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public User searchUserById(@Context SecurityContext context, @PathParam("id") final int id) {
        boolean isReader = context.isUserInRole("reader");
        boolean isAdministrator = context.isUserInRole("administrator");

        Principal principal = context.getUserPrincipal();

        System.out.println("Principal: " + principal.getName() + " belongs to reader: " + isReader + " administrator: " + isAdministrator);
        javax.naming.Context ctx = null;
        UserBeanRemote userBean = null;
        try {
            ctx = new InitialContext();
            userBean = (UserBeanRemote) ctx.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
        } catch (NamingException exception) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, exception);
        }
        System.out.println("Searching for user: " + id);
        User user = userBean.searchUserById(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

    @RolesAllowed({"administrator"})
    @Path("/newuser")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response insertUser(String userXML) {
        javax.naming.Context ctx = null;
        UserBeanRemote userBean = null;
        try {
            ctx = new InitialContext();
            userBean = (UserBeanRemote) ctx.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
        } catch (NamingException exception) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, exception);
        }

        int id = 0;
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(User.class);
            Unmarshaller u = jaxbc.createUnmarshaller();
            StringReader reader = new StringReader(userXML);
            User unmarshalled = (User) u.unmarshal(reader);
            User user = userBean.createUser(unmarshalled);
            id = user.getId();
        } catch (JAXBException exception) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return Response.created(URI.create("/user/" + id)).build();
    }

    @RolesAllowed({"administrator"})
    @DELETE
    @Path("/{id}")
    public Response removeUserById(@PathParam("id") final int id) {
        javax.naming.Context ctx = null;
        UserBeanRemote userBean = null;
        try {
            ctx = new InitialContext();
            userBean = (UserBeanRemote) ctx.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
        } catch (NamingException exception) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, exception);
        }
        userBean.removeUser(id);

        return Response.status(Status.GONE).build();
    }
}
