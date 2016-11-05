/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.web.controllers.impl;

import com.lntormin.javaee.ejb.beans.UserBeanRemote;
import com.lntormin.javaee.ejb.entities.User;
import com.lntormin.javaee.web.controllers.AbstractController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author lntormin
 */
public class NewController extends AbstractController {
    @Override
    public void execute() {
        try {
            Logger.getLogger(NewController.class.getName()).log(Level.INFO, null, "New");

            Context context = new InitialContext();
            UserBeanRemote userBean = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");

            String name = this.getRequest().getParameter("name");
            String surname = this.getRequest().getParameter("surname");
            String userName = this.getRequest().getParameter("username");
            String password = this.getRequest().getParameter("password");
            
            Logger.getLogger(NewController.class.getName()).log(Level.INFO,"Surname: "+surname+"  Username: "+userName);

            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(userName);
            user.setHash(userBean.getHash(password));
            user = userBean.createUser(user);
            this.setReturnPage("/displayUser.jsp");
            this.getRequest().setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(NewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}