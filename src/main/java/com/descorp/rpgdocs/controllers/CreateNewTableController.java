/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.RpgTable;
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
@ManagedBean(name = "createNewTableController")
@ViewScoped
public class CreateNewTableController {
    private RpgTableRepositoryImpl repo;
    private RpgTable table;

    public CreateNewTableController() {
        this.table = new RpgTable();
        
        this.repo = RpgTableRepositoryImpl.getInstance();
        
        this.table.setPlayers(new ArrayList<>());
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");

        if (user != null) {
            this.table.setMaster(user);
        }
    }

    public RpgTable getTable() {
        return table;
    }

    public void setTable(RpgTable table) {
        this.table = table;
    }
    
    public void save() {
        if(this.repo.saveRpgTable(this.table) != null) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('createdTableModal').show();");
        }   
    }
    
    
    
}
