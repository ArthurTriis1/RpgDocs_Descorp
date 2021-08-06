/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.RpgTable;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.RpgTableRepositoryImpl;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "enterTableController")
@ViewScoped
public class EnterBoardController {

    User user;
    
    RpgTableRepositoryImpl repo;
    
    String code;
    
    Long sheetId;

    public EnterBoardController() {
        this.repo = RpgTableRepositoryImpl.getInstance();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User actualUser = (User) session.getAttribute("user");

        if (actualUser != null) {
            this.user = actualUser;
        }
    }
    
    public void save(){
        RpgTable table = this.repo.getRpgTableByHash(code);
        
        if(table != null){
            //Add player on table
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('enterTableSuccess').show();");
        }else {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('enterTableError').show();");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RpgTableRepositoryImpl getRepo() {
        return repo;
    }

    public void setRepo(RpgTableRepositoryImpl repo) {
        this.repo = repo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    
}
