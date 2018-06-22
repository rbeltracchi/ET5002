/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan111mil.repositories;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jud
 */
class DBManagerRepository implements Serializable {
    private EntityManagerFactory emf;
    private EntityManager manager;

    public DBManagerRepository() {
	this.emf = Persistence.createEntityManagerFactory("SCATpersistence");
	this.manager = emf.createEntityManager();
    }
        
            /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }

    /**
     * @param emf the emf to set
     */
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * @return the manager
     */
    public EntityManager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }
    
    public void cerrarConexiones() {
        this.getManager().close();
        this.getEmf().close();
	}
    
}
