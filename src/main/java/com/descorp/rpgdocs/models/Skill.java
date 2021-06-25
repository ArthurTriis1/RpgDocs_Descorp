/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Skill implements Serializable{
    
    @Column(name = "STRONG")
    private Integer strong;
    
    @Column(name = "DEXTERITY")
    private Integer dexterity;
    
    @Column(name = "INTELLIGENCE")
    private Integer intelligence;
    
    @Column(name = "CHARM")
    private Integer charm;

    public Integer getStrong() {
        return strong;
    }

    public void setStrong(Integer strong) {
        this.strong = strong;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getCharm() {
        return charm;
    }

    public void setCharm(Integer charm) {
        this.charm = charm;
    }
}
