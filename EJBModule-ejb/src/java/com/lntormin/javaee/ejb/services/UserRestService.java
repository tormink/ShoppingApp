package com.lntormin.javaee.ejb.services;

import com.lntormin.javaee.ejb.beans.UserBean;
import com.lntormin.javaee.ejb.entities.User;
import java.io.StringReader;
import java.net.URI;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author lntormin
 */
@Stateless
@LocalBean
@Path("/users/")
public class UserRestService {
    @EJB
    UserBean userBean;
    
    @GET
    @Path("/user/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User searchUserById(@PathParam("id") final int id){
        System.out.println("Searching user: "+id);
        User user = userBean.searchUserById(id);
        if(user==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }
    
    @PUT
    @Path("/newuser")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(String userXML){
        int id=0;
        try{
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(userXML);
            User xmlUser = (User) unmarshaller.unmarshal(reader);
            User user=userBean.createUser(xmlUser);
            id = user.getId();
        } catch(JAXBException exception){
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return Response.created(URI.create("/user/"+id)).build();
    }
}
