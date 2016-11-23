package com.lntormin.javaee.web.rest;

import com.lntormin.javaee.web.rest.services.ApplicationRestService;
import com.lntormin.javaee.web.rest.services.UserRestService;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author lntormin
 */
@ApplicationPath("/services")
public class RestApp extends Application{
    private Set<Object> services;
    
    public RestApp(){
        services = new HashSet<>();
        services.add(new UserRestService());
        services.add(new ApplicationRestService());
    }
}
