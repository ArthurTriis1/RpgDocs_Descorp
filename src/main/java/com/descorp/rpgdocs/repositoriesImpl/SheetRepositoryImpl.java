package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.connection.EntityManagerHelper;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class SheetRepositoryImpl {
    
    private static SheetRepositoryImpl sheetRepositoryImpl;
    
    public SheetRepositoryImpl(){ }
    
    public static SheetRepositoryImpl getInstance(){
        if(sheetRepositoryImpl == null){
            sheetRepositoryImpl = new SheetRepositoryImpl();
        }
        return sheetRepositoryImpl;
    }
    
    public Sheet getSheetById(Long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        return em.find(Sheet.class, id);
    }
    
    public Sheet getSheetByName(String name) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.name = :name", Sheet.class);
        q.setParameter("name", name);
        return q.getSingleResult(); 
    }

    public List<Sheet> getSheetsByOwner(User owner) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.owner = :owner_id", Sheet.class);
        q.setParameter("owner_id", owner);
        List<Sheet> resp = q.getResultList();
        return resp;
    }
    
    @Transactional
    public Sheet saveSheet(Sheet sheet) {
       
        
        if (sheet.getId() == null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.persist(sheet);
            em.getTransaction().commit();
            EntityManagerHelper.closeEntityManager();
            return sheet;
        }
        return null;
        
    }

    public Sheet updateSheet(Sheet sheet) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        
        if (sheet.getId() != null) {
            
            em.detach(sheet);
            em.getTransaction().begin();
            em.merge(sheet);
            em.getTransaction().commit();
            EntityManagerHelper.closeEntityManager();
            return sheet;
        }
        return null;
    }
    
    public void deleteSheet(Sheet sheet) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (sheet.getId() != null) {
            em.getTransaction().begin();
            em.remove(sheet);
            em.getTransaction().commit();
            EntityManagerHelper.closeEntityManager();
        }
    }
}
