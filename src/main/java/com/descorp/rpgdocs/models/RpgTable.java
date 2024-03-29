/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TB_RPG_TABLE")
public class RpgTable implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "IDENTIFIER", nullable = false)
    private String identifier;
    
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_MASTER", referencedColumnName = "ID")
    private User master;
    
    @OneToMany(mappedBy = "rpgTable", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Sheet> sheets;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setPlayers(ArrayList<Sheet> sheets) {
        this.sheets = sheets;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void addCheet(Sheet sheet) {
        if (this.sheets == null){
            this.sheets = new ArrayList<>();
        }
        
        this.sheets.add(sheet);
    }  
    
    public void removeSheet(Sheet sheet) {
        if (this.sheets == null){
            this.sheets = new ArrayList<>();
        }
        
        this.sheets.remove(sheet);
    }
    
}
