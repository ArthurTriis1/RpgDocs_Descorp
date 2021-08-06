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
import com.descorp.rpgdocs.repositoriesImpl.RpgTableRepositoryImpl;
import com.descorp.rpgdocs.repositoriesImpl.SheetRepositoryImpl;
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
    
    RpgTable table;
    
    List<Sheet> sheets = new ArrayList<>();
    
    

    public TableOwnerView() {
        this.repo = RpgTableRepositoryImpl.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestParams = context.getExternalContext().getRequestParameterMap();
        String id = (String) requestParams.get("id");
        
        if (id != null) {
            RpgTable findedTable = this.repo.getRpgTableById(Long.valueOf(id));
            this.table = findedTable;
        }
        
        Sheet sheet1 = new Sheet();
        sheet1.setAge(11);
        sheet1.setDescription("jkjlkjlkj");
        sheet1.setId(1L);
        sheet1.setKlass(Klass.MAGE);
        sheet1.setName("lalala");
        sheet1.setRace(Race.Fate);
        
        Skill skill = new Skill();
        skill.setCharm(11);
        skill.setDexterity(44);
        skill.setIntelligence(77);
        skill.setStrong(88);
        
        sheet1.setSkill(skill);
        sheet1.setTools(new ArrayList<>());
        
        Tool tool = new Tool();
        tool.setDamage("22");
        tool.setId(1L);
        tool.setKind(ToolKind.Fire);
        tool.setSheet(sheet1);
        tool.setName("Nome da arma");
        
        sheet1.getTools().add(tool);
        sheet1.getTools().add(tool);
        sheet1.getTools().add(tool);
        
        
        sheets.add(sheet1);
        sheets.add(sheet1);
        sheets.add(sheet1);
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

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }
}
