
package com.lntormin.javaee.ejb.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lntormin.javaee.ejb.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InsertUser_QNAME = new QName("http://services.ejb.javaee.lntormin.com/", "insertUser");
    private final static QName _FindById_QNAME = new QName("http://services.ejb.javaee.lntormin.com/", "findById");
    private final static QName _FindByIdResponse_QNAME = new QName("http://services.ejb.javaee.lntormin.com/", "findByIdResponse");
    private final static QName _InsertUserResponse_QNAME = new QName("http://services.ejb.javaee.lntormin.com/", "insertUserResponse");
    private final static QName _User_QNAME = new QName("http://services.ejb.javaee.lntormin.com/", "user");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lntormin.javaee.ejb.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertUser }
     * 
     */
    public InsertUser createInsertUser() {
        return new InsertUser();
    }

    /**
     * Create an instance of {@link FindById }
     * 
     */
    public FindById createFindById() {
        return new FindById();
    }

    /**
     * Create an instance of {@link FindByIdResponse }
     * 
     */
    public FindByIdResponse createFindByIdResponse() {
        return new FindByIdResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link InsertUserResponse }
     * 
     */
    public InsertUserResponse createInsertUserResponse() {
        return new InsertUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ejb.javaee.lntormin.com/", name = "insertUser")
    public JAXBElement<InsertUser> createInsertUser(InsertUser value) {
        return new JAXBElement<InsertUser>(_InsertUser_QNAME, InsertUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ejb.javaee.lntormin.com/", name = "findById")
    public JAXBElement<FindById> createFindById(FindById value) {
        return new JAXBElement<FindById>(_FindById_QNAME, FindById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ejb.javaee.lntormin.com/", name = "findByIdResponse")
    public JAXBElement<FindByIdResponse> createFindByIdResponse(FindByIdResponse value) {
        return new JAXBElement<FindByIdResponse>(_FindByIdResponse_QNAME, FindByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ejb.javaee.lntormin.com/", name = "insertUserResponse")
    public JAXBElement<InsertUserResponse> createInsertUserResponse(InsertUserResponse value) {
        return new JAXBElement<InsertUserResponse>(_InsertUserResponse_QNAME, InsertUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.ejb.javaee.lntormin.com/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

}
