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
public class UpdateController extends AbstractController {
    @Override
    public void execute() {
        try {
            Logger.getLogger(UpdateController.class.getName()).log(Level.INFO, null, "Update");

            Context context = new InitialContext();
            UserBeanRemote userBean = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");

            User user = new User();

            String id = this.getRequest().getParameter("id");
            String name = this.getRequest().getParameter("name");
            String surname = this.getRequest().getParameter("surname");
            String username = this.getRequest().getParameter("username");

            user.setId(Integer.parseInt(id));
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            
            userBean.updateUser(user);
            this.setReturnPage("/displayUser.jsp");
            this.getRequest().setAttribute("user", user);

        } catch (Exception ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

