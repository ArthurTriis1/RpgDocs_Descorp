package com.descorp.rpgdocs.services;

import com.descorp.rpgdocs.beans.SignInBean;
import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthService {
    
    private UserRepositoryImpl userRepo;
    
    private static AuthService loginService;
    
    public AuthService(){
        this.userRepo = UserRepositoryImpl.getInstance();
    }
    
    public static AuthService getInstance(){
        if(loginService == null){
            loginService = new AuthService();
        }
        
        return loginService;
    }
    
    public User SignIn(SignInBean bean){
        User loggedUser = userRepo.getUserByEmailAndPassword(bean.getEmail(), bean.getPassword());
        
        if (loggedUser != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().removeAttribute("user");
            request.getSession().setAttribute("user", loggedUser);
            
        }
        
        return loggedUser;
    }
    
    public User SignUp(User user){
        return userRepo.saveUser(user);
    }
    
    public String Logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().removeAttribute("user");
        
        return "/index.xhtml?faces-redirect=true";
    }
    
    public User updateUser(User user) {
        return userRepo.saveUser(user);
    }
    
    public void deleteUser(User user){
        userRepo.deleteUser(user);
    }
    
    public User getLoggedUser(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        User actualUser = (User) session.getAttribute("user");
        
        if(actualUser == null){
            return null;
        }
        
        User findedUser = this.userRepo.getUserById(actualUser.getId());
        
        List<Sheet> sheets = findedUser.getSheets();
        
        return findedUser;
    }
}
