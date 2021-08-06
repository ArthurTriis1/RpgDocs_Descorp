package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Skill;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
import java.util.ArrayList;
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
    
    public SheetController() {
        
        this.sheet = new Sheet();
        this.sheet.setSkill(new Skill());
        this.sheet.setTools(new ArrayList<>());
        
        this.repo = SheetRepositoryImpl.getInstance();
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
