/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.repositories.SheetRepository;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "editSheetController")
@ViewScoped
public class EditSheetController {
    private SheetRepository repo;
    private Sheet sheet;
    
    private Tool newTool;
    
    public EditSheetController() {
        
        this.newTool = new Tool();
        
        this.repo = SheetRepositoryImpl.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestParams = context.getExternalContext().getRequestParameterMap();
        String id = (String) requestParams.get("id");
        
        if (id != null) {
            Sheet findedSheet = this.repo.getSheetById(Long.valueOf(id));
            this.sheet = findedSheet;
        }
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
    
    public void addTool(){
        this.sheet.addTools(newTool);
        this.newTool = new Tool();
    }
    
    public void removeTool(Tool tool){
        this.sheet.removeTools(tool);
    }

    public Tool getNewTool() {
        return newTool;
    }

    public void setNewTool(Tool newTool) {
        this.newTool = newTool;
    }
    
    public void update() {    
        if(this.repo.saveSheet(this.sheet) != null) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('updateSheetModal').show();");
        }

    }
}
