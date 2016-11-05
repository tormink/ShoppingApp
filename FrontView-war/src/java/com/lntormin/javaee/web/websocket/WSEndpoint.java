package com.lntormin.javaee.web.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author lntormin
 */

@ServerEndpoint("/example")
public class WSEndpoint {
    
    @OnMessage
    public String receiveMessage(String message, Session session){
        System.out.println("Received: "+ message + ", session: "+session.getId());
        return "Response from the server";
    }
    
    @OnClose
    public void close(Session session, CloseReason reason){
        System.out.println("Closing: "+ session.getId());
    }
}
