/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.services.AuthService;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author arthur
 */
@ViewScoped
@ManagedBean(name = "docsBarController")
public class DocsBarController {
    public String logout() throws IOException{
        AuthService auth = new AuthService();
        return auth.Logout();
    }
}
