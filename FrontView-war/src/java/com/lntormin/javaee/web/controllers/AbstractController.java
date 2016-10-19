package com.lntormin.javaee.web.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lntormin
 */
public abstract class AbstractController implements Controller {
    protected String returnPage;
    private HttpServletRequest request;

    public void init(HttpServletRequest request) {
        this.setRequest(request); //inicia o controller ao atribuir o request HTTP.
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }
}
