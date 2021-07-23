package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.SheetRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class SheetRepositoryImpl implements SheetRepository{
    
    private static SheetRepositoryImpl sheetRepositoryImpl;
    
    public SheetRepositoryImpl(){

    }
    
    public static SheetRepository getInstance(){
        if(sheetRepositoryImpl == null){
            sheetRepositoryImpl = new SheetRepositoryImpl();
        }
        return sheetRepositoryImpl;
    }
    
    @Override
    public Sheet getSheetById(Long id) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        return em.find(Sheet.class, id);
    }
    
    @Override
    public Sheet getSheetByName(String name) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.name = :name", Sheet.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    @Override
    public List<Sheet> getSheetsByOwner(User owner) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.owner = :owner_id", Sheet.class);
        q.setParameter("owner_id", owner);
        return q.getResultList();
    }
    
    @Override
    public Sheet saveSheet(Sheet sheet) {
        if (sheet.getId() == null) {
            EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
            em.getTransaction().begin();
            em.persist(sheet);
            em.getTransaction().commit();
            return sheet;
        }
        return null;
        
    }

    @Override
    public Sheet updateSheet(Sheet sheet) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (sheet.getId() != null) {
            
            em.detach(sheet);
            em.getTransaction().begin();
            em.merge(sheet);
            em.getTransaction().commit();
            return sheet;
        }
        return null;
    }
    
    @Override
    public void deleteSheet(Sheet sheet) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (sheet.getId() != null) {
            em.getTransaction().begin();
            em.remove(sheet);
            em.getTransaction().commit();
        }
    }

}
