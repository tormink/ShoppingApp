package com.lntormin.javaee.ejb.interceptors;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.*;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author lntormin
 */
public class LogInterceptor {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:/jms/queue/eventQueue")
    private Destination destination;

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        Connection connection = connectionFactory.createConnection(
                System.getProperty("username", "jmsUser"),
                System.getProperty("password", "jmsUser123!"));
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer(destination);
        connection.start();
        TextMessage textMessage = session.createTextMessage(context.getMethod().getName());
        messageProducer.send(textMessage);
        //close connection
        if (connection != null) {
            connection.close();
        }
        System.out.println("---" + context.getMethod());
        return context.proceed();

    }
}
