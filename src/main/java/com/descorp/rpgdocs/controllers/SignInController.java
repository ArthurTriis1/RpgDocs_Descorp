/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.beans.SignInBean;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.services.AuthService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "signInController")
@ViewScoped
public class SignInController {

    private String password;

    private String email;

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

    public String login() {
        SignInBean bean = new SignInBean();

        bean.setEmail(email);
        bean.setPassword(password.hashCode());

        AuthService auth = new AuthService();
        User user = auth.SignIn(bean);

        if (user != null) {
            return "docs/index.xhtml?faces-redirect=true";
        }

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('errLoginVar').show();");

        return "";
    }

}
