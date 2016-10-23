package com.lntormin.javaee.ejb.services;

import com.lntormin.javaee.ejb.beans.UserBean;
import com.lntormin.javaee.ejb.entities.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author lntormin
 */
@Stateless
@LocalBean
@WebService
public class UserService {
    @EJB
    UserBean userBean;
    
    @WebMethod(operationName = "findById")
    public User searchUserById(@WebParam(name = "id") final int id){
        return userBean.searchUserById(id);
    }
    
    @WebMethod(operationName = "insertUser")
    public void createUser(@WebParam(name = "user") final User u){
        userBean.createUser(u);
    }
    
}
