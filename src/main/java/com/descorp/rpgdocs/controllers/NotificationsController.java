/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Invite;
import com.descorp.rpgdocs.models.Notification;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositoriesImpl.InviteRepositoryImpl;
import com.descorp.rpgdocs.services.AuthService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author arthur
 */
@ManagedBean(name = "notificationsController")
@ViewScoped
public class NotificationsController {
    
    Notification confirmNotification;
    
    List<Notification> notifications;
    
    InviteRepositoryImpl inviteRepositoryImpl;
    
    AuthService authService;

    public NotificationsController() {
        
       this.inviteRepositoryImpl = InviteRepositoryImpl.getInstance();
       this.authService = AuthService.getInstance();
       
       User actualUser = this.authService.getLoggedUser();
        
       this.notifications = this.inviteRepositoryImpl.getInvitesByToUser(actualUser).stream().map(invite -> {
           Notification n = new Notification();
           n.setInviteId(invite.getId());
           n.setTarget("enter.xhtml?id="+ invite.getTable().getIdentifier());
           n.setText(invite.getFromUser().getName() + " convidou você para a mesa " + invite.getTable().getName() + ".");
           return n;
       }).collect(Collectors.toList());
    }
    
    public void confirmInvite(Notification noti){
        this.confirmNotification = noti;
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('acceptInviteModal').show();");
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Notification getConfirmNotification() {
        return confirmNotification;
    }

    public void setConfirmNotification(Notification confirmNotification) {
        this.confirmNotification = confirmNotification;
    }
    
    public void rejectInvite() throws IOException{
        Invite invite = this.inviteRepositoryImpl.getInviteById(this.confirmNotification.getInviteId());
        this.inviteRepositoryImpl.deleteInvite(invite);
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void acceptInvite() throws IOException{

        Invite invite = this.inviteRepositoryImpl.getInviteById(this.confirmNotification.getInviteId());
        this.inviteRepositoryImpl.deleteInvite(invite);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.confirmNotification.getTarget());
    }
}
