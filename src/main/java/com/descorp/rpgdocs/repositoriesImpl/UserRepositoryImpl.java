package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.connection.EntityManagerHelper;
import com.descorp.rpgdocs.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class UserRepositoryImpl {
    
    private static UserRepositoryImpl userRepositoryImpl;
    
    public UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance(){
        
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl();
        }
        return userRepositoryImpl;
    }

    public User getUserById(Long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query q = em.createQuery("SELECT u FROM User u WHERE u.id = :identifier", User.class);
        q.setParameter("identifier", id);   
        List<User> listUsers = q.getResultList();
        
        if(listUsers.isEmpty()){
            return null;
        }
        
        User findUser = (User) listUsers.get(0);
        
        return findUser;
    }

    public User getUserByEmailAndPassword(String email, Integer password) {
        EntityManager em = EntityManagerHelper.getEntityManager();
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
    
    public User getUserByEmail(String email) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query q = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        q.setParameter("email", email);   
        List<User> listUsers = q.getResultList();
        
        if(listUsers.isEmpty()){
            return null;
        }
        
        User findUser = (User) listUsers.get(0);
        
        return findUser;
    }

    public User saveUser(User user) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (user.getId() == null) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }
        return null;
        
    }


    public User updateUser(User user) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (user.getId() != null) {
            
            em.detach(user);
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return user;
        }
        return null;
    }
    

    public void deleteUser(User user) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (user.getId() != null) { 
            if (!em.contains(user)) {
                user = em.merge(user);
            }
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }
}
