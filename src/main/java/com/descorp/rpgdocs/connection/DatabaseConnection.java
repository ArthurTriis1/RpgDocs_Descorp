/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author eletr
 */
public class DatabaseConnection {
    
    private EntityManagerFactory emf;
	
    private static DatabaseConnection myself = null;
	
    public DatabaseConnection() {
 
        this.emf = Persistence.createEntityManagerFactory("rpg_docs");
    }
	
    public static DatabaseConnection getCurrentInstance(){
        
        if(myself == null){
            myself = new DatabaseConnection();
        }
        
        return myself;
    }
    
    public EntityManager createEntityManager(){
        return emf.createEntityManager();
    }
}
