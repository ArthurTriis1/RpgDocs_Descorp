package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepository {
    
    private EntityManager em;
    private EntityTransaction et;
    
    public UserRepositoryImpl(EntityManager em){
        this.em = em;
        this.et = em.getTransaction();
        this.et.begin();
    }
    
    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        q.setParameter("email", email);
        return q.getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
            et.commit();
        } else {
            user = em.merge(user);
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        if (em.contains(user)) {
            em.remove(user);
        } else {
            em.clear();
            em.merge(user);
            et.commit();
        }
    }
    
}
