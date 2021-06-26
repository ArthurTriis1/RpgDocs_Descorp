package com.descorp.rpgdocs.repositories;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import java.util.List;

public interface SheetRepository {
    
    public static SheetRepository getInstance(){
        throw new AbstractMethodError();
    };
    
    Sheet getSheetById(Long id);
    
    Sheet getSheetByName(String name);
    
    List<Sheet> getSheetsByOwner(User owner);
    
    Sheet saveSheet(Sheet sheet);
    
    void deleteSheet(Sheet sheet);
}
