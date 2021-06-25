package com.descorp.rpgdocs.repositories;

import com.descorp.rpgdocs.models.Sheet;

public interface SheetRepository {
    
    public static SheetRepository getInstance(){
        throw new AbstractMethodError();
    };
    
    Sheet getSheetById(Long id);
    
    Sheet getSheetByName(String name);
    
    Sheet saveSheet(Sheet sheet);
    
    void deleteSheet(Sheet sheet);
}
