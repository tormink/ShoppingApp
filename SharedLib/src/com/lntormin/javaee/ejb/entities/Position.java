package com.lntormin.javaee.ejb.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lntormin
 */
@Entity
@Table(name = "tb_posicao")
@XmlRootElement()
public class Position {

    @Id
    @Column(name = "posicao_id")
    @SequenceGenerator(name = "posicaoGenerator", sequenceName = "posicao_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posicaoGenerator")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "lat")
    private String latitude;

    @Column(name = "timestamp")
    protected Timestamp timestamp;

    @Column(name = "long")
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
