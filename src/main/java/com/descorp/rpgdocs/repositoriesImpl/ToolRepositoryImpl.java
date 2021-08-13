package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.connection.EntityManagerHelper;
import com.descorp.rpgdocs.models.Tool;
import javax.persistence.EntityManager;

/**
 *
 * @author eletr
 */
public class ToolRepositoryImpl {
    
    private static ToolRepositoryImpl toolRepositoryImpl;
            
    public ToolRepositoryImpl(){
    }
    
     public static ToolRepositoryImpl getInstance(){
        
        if(toolRepositoryImpl == null){
            toolRepositoryImpl = new ToolRepositoryImpl();
        }
        return toolRepositoryImpl;
    }
    
    public Tool getToolById(Long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        //EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        return em.find(Tool.class, id);
    }

    public Tool saveTool(Tool tool) {
        if (tool.getId() == null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            //EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
            em.getTransaction().begin();
            em.persist(tool);
            em.getTransaction().commit();
            return tool;
        }
        return null;
    }


    public Tool updateTool(Tool tool) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        //EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (tool.getId() != null) {
            
            em.detach(tool);
            em.getTransaction().begin();
            em.merge(tool);
            em.getTransaction().commit();
            return tool;
        }
        return null;
    }
    
    
    public void deleteTool(Tool tool) { 
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (tool.getId() != null) {
            if (!em.contains(tool)) {
                tool = em.merge(tool);
            }
            em.getTransaction().begin();
            em.remove(tool);
            em.getTransaction().commit();
        }

    }
}
