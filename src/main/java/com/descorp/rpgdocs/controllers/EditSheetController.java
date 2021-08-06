/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import java.util.ArrayList;
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
    private SheetRepositoryImpl repo;
    private Sheet sheet;
    
    public EditSheetController() {
        
        this.repo = SheetRepositoryImpl.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestParams = context.getExternalContext().getRequestParameterMap();
        String id = (String) requestParams.get("id");
        this.sheet = new Sheet();
        this.sheet.setTools(new ArrayList<>());
        
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
        this.sheet.addTools(new Tool());
    }
    
    public void removeTool(Tool tool){
        this.sheet.removeTools(tool);
    }
    
    public void removeTool(int index){
        this.sheet.removeToolByIndex(index);
    }
    
    public void update() {    
        if(this.repo.updateSheet(this.sheet) != null) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('updateSheetModal').show();");
        }

    }
}
