/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.services.AuthService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "signUpController")
@ViewScoped
public class SignUpController {

    private String name;

    private String password;
    
    private String confirmPassword;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String signUP() {
        AuthService authService = new AuthService();
        
        if(confirmPassword.equals(password)){
            User newUser = new User();
            newUser.setName(this.name);
            newUser.setEmail(this.email);
            newUser.setPassword(this.password);
            authService.SignUp(newUser);
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('myDialogVar').show();");
        }
        
        return "";
        
    }
    

    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    

}
