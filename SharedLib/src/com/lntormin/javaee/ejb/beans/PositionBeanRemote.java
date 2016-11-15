/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lntormin.javaee.ejb.beans;

import com.lntormin.javaee.ejb.entities.Position;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lntormin
 */
@Remote
public interface PositionBeanRemote {
    public void save(Position p);
    
    public List<Position> list(String userName);
}
