package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.beans.SignInBean;
import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;


public class UserController {
    
    private UserRepository userRepo;
    
    public UserController(){
        this.userRepo = new UserRepositoryImpl(DatabaseConnection.getCurrentInstance().createEntityManager());
    }
    
    public User SignIn(SignInBean bean){
        return userRepo.getUserByEmailAndPassword(bean.getEmail(), bean.getPassword());
    }
    
    public User SignUp(User user){
        return userRepo.saveUser(user);
    }
    
    public User updateUser(User user) {
        return userRepo.saveUser(user);
    }
    
    public void deleteUser(User user){
        userRepo.deleteUser(user);
    }
}
