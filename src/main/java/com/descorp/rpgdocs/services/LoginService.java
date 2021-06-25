package com.descorp.rpgdocs.services;

import com.descorp.rpgdocs.beans.SignInBean;
import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;


public class LoginService {
    
    private UserRepository userRepo;
    
    private static LoginService loginService;
    
    public LoginService(){
        this.userRepo = UserRepositoryImpl.getInstance();
    }
    
    public static LoginService getInstance(){
        if(loginService == null){
            loginService = new LoginService();
        }
        
        return loginService;
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
