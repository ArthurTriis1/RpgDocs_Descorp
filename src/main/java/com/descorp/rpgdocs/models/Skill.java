/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.models;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.strong);
        hash = 17 * hash + Objects.hashCode(this.dexterity);
        hash = 17 * hash + Objects.hashCode(this.intelligence);
        hash = 17 * hash + Objects.hashCode(this.charm);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Skill other = (Skill) obj;
        if (!Objects.equals(this.strong, other.strong)) {
            return false;
        }
        if (!Objects.equals(this.dexterity, other.dexterity)) {
            return false;
        }
        if (!Objects.equals(this.intelligence, other.intelligence)) {
            return false;
        }
        if (!Objects.equals(this.charm, other.charm)) {
            return false;
        }
        return true;
    }
    
    
}
