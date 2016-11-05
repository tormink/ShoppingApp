package com.lntormin.javaee.ejb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lntormin
 */
@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tb_usuario")
public class User implements Serializable{

    @XmlAttribute
    @Id
    @Column(name = "usuario_id")
    @SequenceGenerator(name = "userGenerator", sequenceName = "usuario_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    private int id;

    @XmlElement
    @Column(name = "nome")
    private String name;

    @XmlElement
    @Column(name = "sobrenome")
    private String surname;

    @XmlElement
    @Column(name = "login")
    private String username;

    @XmlElement
    @Column(name = "hash")
    private String hash;

    public User() {
    }

    public User(int id, String name, String surname, String username, String hash) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        String sbResult = "id = " +
                id +
                ", nome = " +
                name +
                ", sobrenome = " +
                surname;
        return sbResult;
    }
}

