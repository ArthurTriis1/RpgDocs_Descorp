package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager em;
    private EntityTransaction et;
    private static UserRepositoryImpl userRepositoryImpl;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
        this.et = em.getTransaction();
        this.et.begin();
    }

    public static UserRepositoryImpl getInstance() {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();

        if (userRepositoryImpl == null) {
            userRepositoryImpl = new UserRepositoryImpl(em);
        }

        return userRepositoryImpl;
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByEmailAndPassword(String email, Integer password) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        q.setParameter("email", email);
        q.setParameter("password", password);
        
        List<User> listUsers = q.getResultList();
        
        if(listUsers.isEmpty()){
            return null;
        }
        
        User findUser = (User) listUsers.get(0);
        
        return findUser;
         
    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
            et.commit();
            et.begin();
        } else {
            em.clear();
            user = em.merge(user);
            et.commit();
            et.begin();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        if (em.contains(user)) {
            em.remove(user);
            et.commit();
            et.begin();
        }
    }

}
