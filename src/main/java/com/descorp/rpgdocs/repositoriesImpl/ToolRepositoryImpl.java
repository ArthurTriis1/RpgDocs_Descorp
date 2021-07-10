/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.repositories.ToolRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author eletr
 */
public class ToolRepositoryImpl implements ToolRepository{
    
    private EntityManager em;
    private EntityTransaction et;
            
    public ToolRepositoryImpl(EntityManager em){
        this.em = em;
        this.et = em.getTransaction();
        this.et.begin();
    }
    
    @Override
    public Tool getToolById(Long id) {
        return em.find(Tool.class, id);
    }

    @Override
    public Tool saveTool(Tool tool) {
        if (tool.getId() == null) {
            em.persist(tool);
            et.commit();
            et.begin();
        } else {
           em.clear();
           tool = em.merge(tool);
           et.commit();
           et.begin();
        }
        return tool;
    }

    @Override
    public void deleteTool(Tool tool) {
        if (em.contains(tool)) {
            em.remove(tool);
            et.commit();
            et.begin();
        }
    }
}
