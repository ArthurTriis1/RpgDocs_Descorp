package com.descorp.rpgdocs.beans;

import com.descorp.rpgdocs.controllers.SheetController;
import enums.Klass;
import enums.Race;
import enums.ToolKind;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author David
 */
@ManagedBean
@javax.faces.bean.ManagedBean(name = "sheetBean")
@ViewScoped
public class SheetBean {
    
    private String name;
    private Race race;
    private Integer age;
    private Klass klass;
    private String desc;
    private Integer charm;
    private Integer dexterity;
    private Integer intelligence;
    private Integer strength;
    
    private String toolname;
    private String tooldamage;
    private ToolKind toolkind;

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

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCharm() {
        return charm;
    }

    public void setCharm(Integer charm) {
        this.charm = charm;
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

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }
    
    public Klass[] getKlasses(){
        return Klass.values();
    }
    
    public Race[] getRaces(){
        return Race.values();
    }

    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    public String getTooldamage() {
        return tooldamage;
    }

    public void setTooldamage(String damage) {
        this.tooldamage = damage;
    }

    public ToolKind getToolkind() {
        return toolkind;
    }

    public void setToolkind(ToolKind kind) {
        this.toolkind = kind;
    }
    
    public ToolKind[] getToolKinds(){
        return ToolKind.values();
    }
    
    public void save() {
        SheetController.save(this);
    }
}
