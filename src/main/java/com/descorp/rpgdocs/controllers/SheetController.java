package com.descorp.rpgdocs.controllers;


import com.descorp.rpgdocs.beans.SheetBean;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Skill;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.SheetRepository;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
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
    
    private SheetRepository repo;
    private Sheet sheetView;
    
    public SheetController() {
        this.repo = SheetRepositoryImpl.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestParams = context.getExternalContext().getRequestParameterMap();
        String id = (String) requestParams.get("id");
        
        if (id != null) {
            this.sheetView = this.repo.getSheetById(Long.valueOf(id));
        }
    }

    public Sheet getSheetView() {
        return sheetView;
    }

    public void setSheetView(Sheet sheetView) {
        this.sheetView = sheetView;
    }
    
    public void update(){
        this.repo.saveSheet(sheetView);
    }
    
    public static void save(SheetBean bean) {
        
        SheetRepository repo = SheetRepositoryImpl.getInstance();
        Sheet s = new Sheet();
        
        s.setName(bean.getName());
        s.setDescription(bean.getDesc());
        s.setAge(bean.getAge());
        s.setKlass(bean.getKlass());
        s.setRace(bean.getRace());
        
        Skill sk = new Skill();
        
        sk.setCharm(bean.getCharm());
        sk.setDexterity(bean.getDexterity());
        sk.setIntelligence(bean.getIntelligence());
        sk.setStrong(bean.getStrength());
        
        s.setSkill(sk);
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        User owner = (User) request.getSession().getAttribute("user");
        s.setOwner(owner);
        
        
        Tool t = new Tool();
        t.setName(bean.getToolname());
        t.setKind(bean.getToolkind());
        t.setDamage(bean.getTooldamage());
        
        s.addTools(t);
        
        
        if(repo.saveSheet(s) != null) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('createdSheetModal').show();");
        }
        
        
    }
}
