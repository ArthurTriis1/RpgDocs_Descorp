package com.descorp.rpgdocs.repositoriesImpl;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager em;
    private EntityTransaction et;
    private static UserRepositoryImpl userRepositoryImpl;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
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
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        q.setParameter("email", email);
        q.setParameter("password", password);
        return q.getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        this.et = em.getTransaction();
        this.et.begin();
        if (user.getId() == null) {
            em.persist(user);
            et.commit();
        } else {
            em.clear();
            user = em.merge(user);
            et.commit();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        if (em.contains(user)) {
            em.remove(user);
        } else {
            em.merge(user);
        }
    }

}
