/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.SheetRepository;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "docsListController")
@SessionScoped
public class DocsListController {

    String name;

    SheetRepository sheetRepository;

    public DocsListController() {
        //this.sheetRepository = SheetRepositoryImpl.getInstance();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            this.name = "sessão vazia";
        } else {
            this.name = user.getName();

        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
