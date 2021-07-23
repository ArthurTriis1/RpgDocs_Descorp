package com.descorp.rpgdocs.repositories;

import com.descorp.rpgdocs.models.Tool;

public interface ToolRepository {
    
    Tool getToolById(Long id);
    
    Tool saveTool(Tool tool);
    
    Tool updateTool(Tool tool);
   
    void deleteTool(Tool tool); 
}
