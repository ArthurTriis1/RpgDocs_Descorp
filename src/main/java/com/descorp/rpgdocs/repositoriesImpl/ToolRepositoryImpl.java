package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.models.Tool;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author eletr
 */
public class ToolRepositoryImpl {
    
    private EntityManager em;
    private EntityTransaction et;
            
    public ToolRepositoryImpl(EntityManager em){
        this.em = em;
        this.et = em.getTransaction();
        this.et.begin();
    }
    
    public Tool getToolById(Long id) {
        return em.find(Tool.class, id);
    }

    public Tool saveTool(Tool tool) {
        if (tool.getId() == null) {
            em.persist(tool);
            et.commit();
        } else {
           em.clear();
           tool = em.merge(tool);
           et.commit();
        }
        return tool;
    }

    public void deleteTool(Tool tool) {
        if (em.contains(tool)) {
            em.remove(tool);
        } else {
            em.merge(tool);
        }
    }
}
