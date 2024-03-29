/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.EntityManagerHelper;
import com.descorp.rpgdocs.models.RpgTable;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


public class RpgTableRepositoryImpl {
    
    private static RpgTableRepositoryImpl rpgTableRepositoryImpl;
    
    public RpgTableRepositoryImpl(){ }
    
    public static RpgTableRepositoryImpl getInstance(){
        if(rpgTableRepositoryImpl == null){
            rpgTableRepositoryImpl = new RpgTableRepositoryImpl();
        }
        return rpgTableRepositoryImpl;
    }
    
    public RpgTable getRpgTableById(Long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        return em.find(RpgTable.class, id);
    }
    
    public RpgTable getRpgTableByName(String name) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<RpgTable> q = em.createQuery("SELECT T FROM RpgTable T WHERE T.name = :name", RpgTable.class);
        q.setParameter("name", name);
        return q.getSingleResult(); 
    }
    
    public List<RpgTable> getRpgTablesByPlayer(User player) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<RpgTable> q = em.createQuery("SELECT T FROM RpgTable T JOIN T.sheets p WHERE P.owner.id = :player", RpgTable.class);
        q.setParameter("player", player.getId());
        List<RpgTable> resp = q.getResultList();
        return resp;
    }
    
    
    public RpgTable getRpgTableByIdentifier(String identifier) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<RpgTable> q = em.createQuery("SELECT T FROM RpgTable T WHERE T.identifier = :identifier", RpgTable.class);
        q.setParameter("identifier", identifier);
        return q.getSingleResult(); 
    }

    public List<RpgTable> getRpgTablesByMaster(User master) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<RpgTable> q = em.createQuery("SELECT T FROM RpgTable T WHERE T.master = :master", RpgTable.class);
        q.setParameter("master", master);
        List<RpgTable> resp = q.getResultList();
        return resp;
    }
    
//    public void addSheetInRpgTables(RpgTable table, Long sheetId) {
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        TypedQuery q = em.createQuery("UPDATE Sheet s SET s.rpgTable = :rpgTable WHERE s.id = :sheet", RpgTable.class);
//        q.setParameter("sheet", sheetId);
//        q.setParameter("rpgTable", table);
//        q.();
//    }
    
    @Transactional
    public RpgTable saveRpgTable(RpgTable table) {
        if (table.getId() == null) {
            
            Random aleatorio = new Random();
            Integer hash = aleatorio.nextInt(1000) + 1;
            
            table.setIdentifier(table.getName().replaceAll(" ","_").toLowerCase() + "-" + hash.toString());
            
            EntityManager em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.persist(table);
            em.getTransaction().commit();
            return table;
        }
        return null;
    }

    public RpgTable updateRpgTable(RpgTable table) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        
        if (table.getId() != null) {
            em.detach(table);
            em.getTransaction().begin();
            em.merge(table);
            em.getTransaction().commit();
            return table;
        }
        return null;
    }
    
    public void deleteRpgTable(RpgTable table) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (table.getId() != null) {
            if (!em.contains(table)) {
                table = em.merge(table);
            }
            em.getTransaction().begin();
            em.remove(table);
            em.getTransaction().commit();
        }
    }
}
