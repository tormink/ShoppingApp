/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.app;

import com.lntormin.javaee.ejb.services.User;
import com.lntormin.javaee.ejb.services.UserService;
import com.lntormin.javaee.ejb.services.UserServiceService;

/**
 *
 * @author lntormin
 */
public class MySOAPApp {
    public static void main(String[] args) {
        UserServiceService uss = new UserServiceService();
        UserService us = uss.getUserServicePort();
        User user = new User();
        user.setId(10);
        user.setName("Name");
        user.setSurname("Surname");
        us.insertUser(user);
        User userReturn = us.findById(1);
        System.out.println(userReturn.getId()+" "+userReturn.getName()+" "+userReturn.getSurname());
    }
}
