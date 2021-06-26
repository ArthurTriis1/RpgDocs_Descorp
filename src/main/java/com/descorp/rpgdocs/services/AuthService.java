package com.descorp.rpgdocs.services;

import com.descorp.rpgdocs.beans.SignInBean;
import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


public class AuthService {
    
    private UserRepository userRepo;
    
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
}
