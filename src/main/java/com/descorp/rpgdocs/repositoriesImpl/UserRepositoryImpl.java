package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserRepositoryImpl implements UserRepository {
    
    private static UserRepositoryImpl userRepositoryImpl;
    
    public UserRepositoryImpl() {
    }

    public static UserRepository getInstance(){
        
        if(userRepositoryImpl == null){
            userRepositoryImpl = new UserRepositoryImpl();
        }
        return userRepositoryImpl;
    }
    
    @Override
    public User getUserById(Long id) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User getUserByEmailAndPassword(String email, Integer password) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
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
            EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }
        return null;
        
    }

    @Override
    public User updateUser(User user) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (user.getId() != null) {
            
            em.detach(user);
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return user;
        }
        return null;
    }
    
    @Override
    public void deleteUser(User user) {
        EntityManager em = DatabaseConnection.getCurrentInstance().createEntityManager();
        if (user.getId() != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }
}
