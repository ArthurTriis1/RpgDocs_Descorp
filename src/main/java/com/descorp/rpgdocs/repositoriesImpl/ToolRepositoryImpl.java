/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.repositories.ToolRepository;
import javax.persistence.EntityManager;

/**
 *
 * @author eletr
 */
public class ToolRepositoryImpl implements ToolRepository{
    
    private static ToolRepositoryImpl toolRepositoryImpl;
            
    public ToolRepositoryImpl(){
    }
    
     public static ToolRepository getInstance(){
        
        if(toolRepositoryImpl == null){
            toolRepositoryImpl = new ToolRepositoryImpl();
        }
        return toolRepositoryImpl;
    }
    
    @Override
    public Tool getToolById(Long id) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        return em.find(Tool.class, id);
    }

    @Override
    public Tool saveTool(Tool tool) {
        if (tool.getId() == null) {
            EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
            em.getTransaction().begin();
            em.persist(tool);
            em.getTransaction().commit();
            return tool;
        }
        return null;
    }

    @Override
    public Tool updateTool(Tool tool) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (tool.getId() != null) {
            
            em.detach(tool);
            em.getTransaction().begin();
            em.merge(tool);
            em.getTransaction().commit();
            return tool;
        }
        return null;
    }
    
    
    @Override
    public void deleteTool(Tool tool) { 
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (tool.getId() != null) {
            em.getTransaction().begin();
            em.remove(tool);
            em.getTransaction().commit();
        }

    }
}
