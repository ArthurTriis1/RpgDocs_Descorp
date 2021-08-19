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
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import com.descorp.rpgdocs.services.AuthService;
import java.util.List;
import java.util.stream.Collectors;
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
    
    RpgTableRepositoryImpl tableRepo;
    
    SheetRepositoryImpl sheetRepo;
    
    String identifier;
    
    Long sheetId;
    
    List<Sheet> freeSheets;
    
    AuthService authService;

    public EnterBoardController() {
        tableRepo = RpgTableRepositoryImpl.getInstance();
        sheetRepo = SheetRepositoryImpl.getInstance();
        this.authService = AuthService.getInstance();

        User actualUser = this.authService.getLoggedUser();
        
        if (actualUser != null) {
            this.user = actualUser;
            this.freeSheets = actualUser.getSheets().stream().filter(s -> s.getRpgTable() == null).collect(Collectors.toList());
        }
    }
    
    public void save(){
        RpgTable table = this.tableRepo.getRpgTableByIdentifier(identifier);
        Sheet sheet = this.sheetRepo.getSheetById(sheetId);
        
        if(table != null && sheet != null){
            //Add player on table
            
            sheet.setRpgTable(table);
            this.sheetRepo.updateSheet(sheet);

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

    public RpgTableRepositoryImpl getTableRepo() {
        return tableRepo;
    }

    public void setTableRepo(RpgTableRepositoryImpl tableRepo) {
        this.tableRepo = tableRepo;
    }

    public SheetRepositoryImpl getSheetRepo() {
        return sheetRepo;
    }

    public void setSheetRepo(SheetRepositoryImpl sheetRepo) {
        this.sheetRepo = sheetRepo;
    }
    
    

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public List<Sheet> getFreeSheets() {
        return freeSheets;
    }

    public void setFreeSheets(List<Sheet> freeSheets) {
        this.freeSheets = freeSheets;
    }
}
