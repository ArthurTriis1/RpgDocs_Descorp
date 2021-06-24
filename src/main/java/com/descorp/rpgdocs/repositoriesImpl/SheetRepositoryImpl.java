package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.repositories.SheetRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class SheetRepositoryImpl implements SheetRepository{
    
    private EntityManager em;
    private EntityTransaction et;
    
    public SheetRepositoryImpl(EntityManager em){
        this.em = em;
        this.et = em.getTransaction();
        this.et.begin();
    }
    
    @Override
    public Sheet getSheetById(Long id) {
        return em.find(Sheet.class, id);
    }

    @Override
    public Sheet getSheetByName(String name) {
        TypedQuery<Sheet> q = em.createQuery("SELECT s FROM Sheet s WHERE s.name = :name", Sheet.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    @Override
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

    @Override
    public void deleteSheet(Sheet sheet) {
        if (em.contains(sheet)) {
            em.remove(sheet);
        } else {
            em.merge(sheet);
        }
    }
    
}
