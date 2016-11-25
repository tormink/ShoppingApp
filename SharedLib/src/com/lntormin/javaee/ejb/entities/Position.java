package com.lntormin.javaee.ejb.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author lntormin
 */
@Entity
@Table(name = "tb_posicao")
@XmlRootElement(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable{

    @Id
    @Column(name = "posicao_id")
    @SequenceGenerator(name = "posicaoGenerator", sequenceName = "posicao_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posicaoGenerator")
    @XmlAttribute
    private int id;

    @Column(name = "login")
    @XmlElement
    private String login;

    @Column(name = "lat")
    @XmlElement
    private String latitude;

    @Column(name = "timestamp")
    @XmlElement
    @XmlJavaTypeAdapter(TimestampAdapter.class)
    protected Timestamp timestamp;

    @Column(name = "long")
    @XmlElement
    private String longitude;

    public Position() {

    }

    public Position(int id, String login, String latitude, Timestamp timestamp, String longitude) {
        this.id = id;
        this.login = login;
        this.latitude = latitude;
        this.timestamp = timestamp;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    //
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
