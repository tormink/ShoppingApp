/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.ejb.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author lntormin
 */
public class TimestampAdapter extends XmlAdapter<String, Timestamp> {
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public String marshal(Timestamp v) throws Exception {
        return dateFormat.format(v);
    }
    
    @Override
    public Timestamp unmarshal(String v) throws Exception{
        return new Timestamp(dateFormat.parse(v).getTime());
    }
}
