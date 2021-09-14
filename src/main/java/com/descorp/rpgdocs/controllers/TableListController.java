package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Invite;
import com.descorp.rpgdocs.models.RpgTable;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.modules.EmailSenderModule;
import com.descorp.rpgdocs.modules.EmailTemplateBean;
import com.descorp.rpgdocs.repositoriesImpl.InviteRepositoryImpl;
import com.descorp.rpgdocs.repositoriesImpl.RpgTableRepositoryImpl;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "tableListController")
@SessionScoped
public class TableListController {
    User user;

    RpgTableRepositoryImpl tableRepository;
    
    UserRepositoryImpl userRepositoryImpl;
    
    User chosenLogin;
    
    List<RpgTable> myTables = new ArrayList<>();
    
    Boolean emptyMyTablesList;
    
    String inviteEmail;
    String asd = "";
    
    RpgTable inviteTable;
    
    

    public TableListController() {
        this.tableRepository = RpgTableRepositoryImpl.getInstance();
        this.userRepositoryImpl = UserRepositoryImpl.getInstance();

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

    public RpgTable getInviteTable() {
        return inviteTable;
    }
    
    
    public List<User> getLogins(String query) {
        String queryLowerCase = query.toLowerCase();
        
        List<String> logins = new ArrayList<>();
        List<User> users = userRepositoryImpl.getAllUsersLogins();
        
        logins = users.stream().map(u -> u.getName()).collect(Collectors.toList());

        
        List<User> response =  users.stream()
                .filter(u -> u.getName().toLowerCase().startsWith(queryLowerCase))
                .collect(Collectors.toList());
        
        return response;
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
    
    public void sendInvite(){
        
        UserRepositoryImpl ur = UserRepositoryImpl.getInstance();
        
        User aux = ur.getUserByEmail(this.getAsd()); 
        
        if(aux != null) {
         
            Invite i = new Invite();
            
            i.setToUser(aux);
            i.setFromUser(user);
            i.setTable(this.inviteTable);
            
            Invite inviteSaved = InviteRepositoryImpl.getInstance().saveInvite(i);
            
            if(inviteSaved != null) {
                
                EmailSenderModule esm = EmailSenderModule.getInstance();
                
                esm.sendNotification(asd, EmailTemplateBean.format(user.getName(), inviteTable.getName(), inviteTable.getIdentifier()));
                
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('inviteSaved').show();");
                return;
            }
        }
         PrimeFaces current = PrimeFaces.current();
         current.executeScript("PF('inviteFail').show();");
    }
    
    public void setInviteTable(RpgTable table){
        this.inviteTable = table;
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('myDialogVar').show();");
    }

    
    
    public User getChosenLogin() {
        return chosenLogin;
    }

    public void setChosenLogin(User chosenLogin) {
        this.chosenLogin = chosenLogin;
    }

    public String getAsd() {
        return asd;
    }

    public void setAsd(String asd) {
        this.asd = asd;
    }
    
    public void onItemSelect(SelectEvent<String> event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Country Selected", event.getObject()));
        String a = event.getObject();
        this.setAsd(a);
        
    }
    
}
