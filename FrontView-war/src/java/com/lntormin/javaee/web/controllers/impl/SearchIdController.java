package com.lntormin.javaee.web.controllers.impl;

import com.lntormin.javaee.ejb.beans.UserBean;
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
public class SearchIdController extends AbstractController {
    @Override
    public void execute() {
        try {
            Logger.getLogger(SearchIdController.class.getName()).log(Level.INFO, null, "Search by Id");

            String id = this.getRequest().getParameter("id");
            Context context = new InitialContext();

            UserBean userBean = (UserBean) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");
            User user = userBean.searchUserById(Integer.parseInt(id));
            this.setReturnPage("/displayUser.jsp");
            this.getRequest().setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(SearchIdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
