package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class SheetRepositoryImpl {
    
    private static SheetRepositoryImpl sheetRepositoryImpl;
    
    private EntityManager em;
    private EntityTransaction et;
    
    private SheetRepositoryImpl(EntityManager em){
        this.em = em;
        this.et = em.getTransaction();
        this.et.begin();
    }
    
    
    public static SheetRepositoryImpl getInstance(){
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        
        if(sheetRepositoryImpl == null){
            sheetRepositoryImpl = new SheetRepositoryImpl(em);
        }
        return sheetRepositoryImpl;
    }
    
    public Sheet getSheetById(Long id) {
        return em.find(Sheet.class, id);
    }
    
    public Sheet getSheetByName(String name) {
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.name = :name", Sheet.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<Sheet> getSheetsByOwner(User owner) {
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.owner = :owner_id", Sheet.class);
        q.setParameter("owner_id", owner);
        return q.getResultList();
    }
    
    @Transactional
    public Sheet saveSheet(Sheet sheet) {
         if (sheet.getId() == null) {
            em.persist(sheet);
            et.commit();
        } else {
            em.clear();
            sheet = em.merge(sheet);
            et.commit();
        }
        return sheet;
    }

    public void deleteSheet(Sheet sheet) {
        if (sheet.getId() != null) {
            em.remove(sheet);
            et.commit();
        } 
    }
}
