package com.descorp.rpgdocs.controllers;


import com.descorp.rpgdocs.beans.SheetBean;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Skill;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;


/**
 *
 * @author David
 */
@ManagedBean(name = "sheetController")
@ViewScoped
public class SheetController {
    
    private SheetRepositoryImpl repo;
    private Sheet sheet;
    private Tool newTool;
    
    public SheetController() {
        

        this.newTool = new Tool();
        this.sheet = new Sheet();
        this.sheet.setSkill(new Skill());
        this.sheet.setTools(new ArrayList<>());
        
        this.repo = SheetRepositoryImpl.getInstance();
//        FacesContext context = FacesContext.getCurrentInstance();
//        Map requestParams = context.getExternalContext().getRequestParameterMap();
//        String id = (String) requestParams.get("id");
//        
//        if (id != null) {
//            this.sheetView = this.repo.getSheetById(Long.valueOf(id));
//        }
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
    
    public void save() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        User owner = (User) request.getSession().getAttribute("user");
        sheet.setOwner(owner);
        
        if(this.repo.saveSheet(this.sheet) != null) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('createdSheetModal').show();");
        }
        
    }
}
