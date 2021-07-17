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
    
    public Klass[] getKlasses(){
        return Klass.values();
    }
    
    public Race[] getRaces(){
        return Race.values();
    }
    
    public ToolKind[] getToolKinds(){
        return ToolKind.values();
    }
    
}
