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

    public TableOwnerView() {
        this.repo = RpgTableRepositoryImpl.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestParams = context.getExternalContext().getRequestParameterMap();
        String id = (String) requestParams.get("id");
        
        if (id != null) {
            RpgTable findedTable = this.repo.getRpgTableById(Long.valueOf(id));
            this.table = findedTable;
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
}
