package com.descorp.rpgdocs.repositories;

import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.User;
import java.util.List;

public interface SheetRepository {
    
    Sheet getSheetById(Long id);
    
    Sheet getSheetByName(String name);
    
    List<Sheet> getSheetsByOwner(User owner);
    
    Sheet saveSheet(Sheet sheet);
    
    Sheet updateSheet(Sheet sheet);
    
    void deleteSheet(Sheet sheet);
}
