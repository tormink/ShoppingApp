package com.lntormin.javaee.web.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lntormin
 */
public interface Controller {
    void execute();

    void init(HttpServletRequest request);

    String getReturnPage();

}
