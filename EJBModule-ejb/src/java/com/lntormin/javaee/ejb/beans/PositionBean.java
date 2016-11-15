/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.ejb.beans;

import com.lntormin.javaee.ejb.entities.Position;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lntormin
 */
@Stateless
public class PositionBean {
    
    @PersistenceContext(unitName = "DerbyPU")
    private EntityManager em;
    
    public void save(Position p){
        em.persist(p);
    }
    
    public List<Position> list(String username){
        Query query = em.createQuery("FROM Position p where p.login='"+username+"'");
        
        List<Position> list = query.getResultList();
        return list;
    }
}
