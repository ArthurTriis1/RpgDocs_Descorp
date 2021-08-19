/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Invite;
import com.descorp.rpgdocs.models.RpgTable;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.InviteRepositoryImpl;
import com.descorp.rpgdocs.repositoriesImpl.RpgTableRepositoryImpl;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "tableListController")
@ViewScoped
public class TableListController {
    User user;

    RpgTableRepositoryImpl tableRepository;
    
    List<RpgTable> myTables = new ArrayList<>();
    
    Boolean emptyMyTablesList;
    
    String inviteEmail;

    public TableListController() {
        this.tableRepository = RpgTableRepositoryImpl.getInstance();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User actualUser = (User) session.getAttribute("user");

        if (user == null) {
            this.user = actualUser;
        }
        
        this.initTablesList();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RpgTableRepositoryImpl getTableRepository() {
        return tableRepository;
    }

    public void setTableRepository(RpgTableRepositoryImpl tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<RpgTable> getMyTables() {
        return myTables;
    }

    public void setMyTables(List<RpgTable> myTables) {
        this.myTables = myTables;
    }

    public Boolean getEmptyMyTablesList() {
        return emptyMyTablesList;
    }

    public void setEmptyMyTablesList(Boolean emptyMyTablesList) {
        this.emptyMyTablesList = emptyMyTablesList;
    }

    public String getInviteEmail() {
        return inviteEmail;
    }

    public void setInviteEmail(String inviteEmail) {
        this.inviteEmail = inviteEmail;
    }

    
    private void initTablesList(){
        
        List<RpgTable> ownerTables = this.tableRepository.getRpgTablesByMaster(this.user);
        List<RpgTable> playerTables = this.tableRepository.getRpgTablesByPlayer(this.user);
        
        if(ownerTables != null){
            this.myTables.addAll(ownerTables);
        }
        
        if(playerTables != null){
            this.myTables.addAll(playerTables);
        }
        
        this.emptyMyTablesList = this.myTables.size() <= 0;
    }
    
    public void sendInvite(String identifier){
        
        UserRepositoryImpl ur = UserRepositoryImpl.getInstance();
        
        User aux = ur.getUserByEmail(inviteEmail);
        
        if(aux != null) {
         
            Invite i = new Invite();
            
            i.setToUser(aux);
            i.setFromUser(user);
            i.setTableIdentifier(identifier);
            
            Invite inviteSaved = InviteRepositoryImpl.getInstance().saveInvite(i);
            
            if(inviteSaved != null) {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('inviteSaved').show();");
                return;
            } 
            
        }
         PrimeFaces current = PrimeFaces.current();
         current.executeScript("PF('inviteFail').show();");
    }
}
