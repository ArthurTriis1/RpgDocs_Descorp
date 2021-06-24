/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.repositories.ToolRepository;
import javax.persistence.EntityManager;

/**
 *
 * @author eletr
 */
public class ToolRepositoryImpl implements ToolRepository{
    
    private EntityManager em;
            
    public ToolRepositoryImpl(EntityManager em){
        this.em = em;
    }
    
    @Override
    public Tool getToolById(Long id) {
        return em.find(Tool.class, id);
    }

    @Override
    public Tool saveTool(Tool tool) {
        if (tool.getId() == null) {
            em.persist(tool);
        } else {
            tool = em.merge(tool);
        }
        return tool;
    }

    @Override
    public void deleteTool(Tool tool) {
        if (em.contains(tool)) {
            em.remove(tool);
        } else {
            em.merge(tool);
        }
    }
}
