/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.EntityManagerHelper;
import com.descorp.rpgdocs.models.Invite;
import com.descorp.rpgdocs.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class InviteRepositoryImpl {
       private static InviteRepositoryImpl inviteRepositoryImpl;
    
    public InviteRepositoryImpl(){ }
    
    public static InviteRepositoryImpl getInstance(){
        if(inviteRepositoryImpl == null){
            inviteRepositoryImpl = new InviteRepositoryImpl();
        }
        return inviteRepositoryImpl;
    }
    
    public Invite getInviteById(Long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        return em.find(Invite.class, id);
    }

    public List<Invite> getInvitesByFromUser(User fromUser) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<Invite> q = em.createQuery("SELECT I FROM Invite I WHERE I.fromUser = :fromUser", Invite.class);
        q.setParameter("fromUser", fromUser);
        List<Invite> resp = q.getResultList();
        return resp;
    }
    
    public List<Invite> getInvitesByToUser(User toUser) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<Invite> q = em.createQuery("SELECT I FROM Invite I WHERE I.toUser = :toUser", Invite.class);
        q.setParameter("toUser", toUser);
        List<Invite> resp = q.getResultList();
        return resp;
    }
    
    public Invite saveInvite(Invite invite) {
        if (invite.getId() == null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.persist(invite);
            em.getTransaction().commit();
            return invite;
        }
        return null; 
    }

    public Invite updateInvite(Invite invite) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        
        if (invite.getId() != null) {
            em.detach(invite);
            em.getTransaction().begin();
            em.merge(invite);
            em.getTransaction().commit();
            return invite;
        }
        return null;
    }
    
    public void deleteInvite(Invite invite) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (invite.getId() != null) {
            if (!em.contains(invite)) {
                invite = em.merge(invite);
            }
            em.getTransaction().begin();
            em.remove(invite);
            em.getTransaction().commit();
        }
    }
}
