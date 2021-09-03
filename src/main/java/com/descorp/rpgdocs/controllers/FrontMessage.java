/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

/**
 *
 * @author Heber
 */
public class FrontMessage {
    private String msg;

    public FrontMessage() {
    }
    
    public FrontMessage(String msg) {
        this.msg = msg;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
