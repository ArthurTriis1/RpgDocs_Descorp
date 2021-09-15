/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import enums.ConnectionType;

/**
 *
 * @author Heber
 */
public class FrontMessage {
    private String identifier;
    private ConnectionType connectionType;

    public FrontMessage() {
    }
    
    public FrontMessage(String identifier, ConnectionType connectionType) {
        this.connectionType = connectionType;
        this.identifier = identifier;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }
    
    public String getidentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
