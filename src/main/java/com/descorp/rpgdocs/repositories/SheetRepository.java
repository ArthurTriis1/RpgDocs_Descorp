package com.descorp.rpgdocs.repositories;

import com.descorp.rpgdocs.models.Sheet;

public interface SheetRepository {
    
    Sheet getSheetById(Long id);
    
    Sheet getSheetByName(String name);
    
    Sheet saveSheet(Sheet sheet);
    
    void deleteSheet(Sheet sheet);
}
