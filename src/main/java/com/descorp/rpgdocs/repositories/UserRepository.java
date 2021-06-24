package com.descorp.rpgdocs.repositories;

import com.descorp.rpgdocs.models.User;


public interface UserRepository {
    
    User getUserById(Long id);
    
    User getUserByEmail(String email);
    
    User saveUser(User user);
    
    void deleteUser(User user);
}
