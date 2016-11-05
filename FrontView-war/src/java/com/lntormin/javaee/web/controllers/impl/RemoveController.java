package com.lntormin.javaee.web.controllers.impl;

import com.lntormin.javaee.ejb.beans.UserBeanRemote;
import com.lntormin.javaee.ejb.entities.User;
import com.lntormin.javaee.web.controllers.AbstractController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author lntormin
 */
public class RemoveController extends AbstractController {
    @Override
    public void execute() {
        try {
            Logger.getLogger(RemoveController.class.getName()).log(Level.INFO, null, "Remove");

            Context context = new InitialContext();
            UserBeanRemote userBean = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");

            String id = this.getRequest().getParameter("id");
            userBean.removeUser(Integer.parseInt(id));

            List<User> users = (List) userBean.list();
            this.setReturnPage("/listUsers.jsp");
            this.getRequest().setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(RemoveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
