/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.models;

import enums.ToolKind;
import java.util.jar.Attributes;

/**
 *
 * @author arthur
 */
class Tools {
    private String name;
    private ToolKind kind;
    private String damage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ToolKind getKind() {
        return kind;
    }

    public void setKind(ToolKind kind) {
        this.kind = kind;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
