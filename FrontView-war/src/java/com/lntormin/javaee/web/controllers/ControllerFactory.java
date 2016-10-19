/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.web.controllers;

/**
 *
 * @author lntormin
 */
public class ControllerFactory {

    private static Controller getControllerByClass(Class actionClass) {
        try {
            return (Controller) actionClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Controller getControllerByFullClassName(String className) {
        try {
            String name = "com.lntormin.javaee.web.controllers.impl." + className + "Controller";
            Class actionClass = Class.forName(name);
            return getControllerByClass(actionClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
