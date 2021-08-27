/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.models;

import enums.ConnectionType;


public class Message {
    private ConnectionType connectionType;
    private int dice;
            
    public Message(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public Message(ConnectionType connectionType, int dice) {
        this.connectionType = connectionType;
        this.dice = dice;
        
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }   
    
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }
}
