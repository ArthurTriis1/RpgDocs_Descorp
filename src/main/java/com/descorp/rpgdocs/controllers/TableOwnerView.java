/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.RpgTable;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Skill;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.RpgTableRepositoryImpl;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import com.descorp.rpgdocs.services.AuthService;
import enums.Klass;
import enums.Race;
import enums.ToolKind;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "tableOwnerView")
@ViewScoped
public class TableOwnerView {
    
    RpgTableRepositoryImpl repo;
    
    SheetRepositoryImpl sheetRepo;
    
    RpgTable table;
    
    List<Sheet> sheetsList;
    
    User user;
    
    AuthService authService;

    public TableOwnerView() {
        this.repo = RpgTableRepositoryImpl.getInstance();
        
        this.sheetRepo = SheetRepositoryImpl.getInstance();
        
        this.authService = AuthService.getInstance();
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestParams = context.getExternalContext().getRequestParameterMap();
        String id = (String) requestParams.get("id");
        this.user = this.authService.getLoggedUser();
        
        if (id != null) {
            RpgTable findedTable = this.repo.getRpgTableById(Long.valueOf(id));
            this.table = findedTable;
            this.sheetsList = this.sheetRepo.getSheetsByRpgTable(findedTable);
        }
    }

    public RpgTableRepositoryImpl getRepo() {
        return repo;
    }

    public void setRepo(RpgTableRepositoryImpl repo) {
        this.repo = repo;
    }

    public RpgTable getTable() {
        return table;
    }

    public void setTable(RpgTable table) {
        this.table = table;
    }

    public List<Sheet> getSheetsList() {
        return sheetsList;
    }

    public void setSheetsList(List<Sheet> sheetsList) {
        this.sheetsList = sheetsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
