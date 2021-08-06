/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.descorp.rpgdocs.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.Klass;
import enums.Race;

/**
 *
 * @author arthur
 */
@Entity
@Table(name="TB_SHEET")
public class Sheet implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "RACE", nullable = false)
    private Race race;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "KLASS", nullable = false)
    private Klass klass;
    
    @Column(name = "AGE", nullable = true)
    private Integer age;
    
    @Embedded
    private Skill skill;
    
    @OneToMany(mappedBy = "sheet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tool> tools; 
    
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_OWNER", referencedColumnName = "ID")
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Collection<Tool> getTools() {
        return tools;
    }

    public void addTools(Tool tool) {
        if (this.tools == null){
            this.tools = new ArrayList<Tool>();
        }
        
        this.tools.add(tool);
        tool.setSheet(this);
    }
    
    public void removeTools(Tool tool) {
        if (this.tools == null){
            this.tools = new ArrayList<Tool>();
        }
        
        this.tools.remove(tool);
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Sheet other = (Sheet) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
    
    
}
