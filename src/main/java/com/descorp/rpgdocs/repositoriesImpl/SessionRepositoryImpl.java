/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.repositoriesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.Session;

/**
 *
 * @author Heber
 */
public class SessionRepositoryImpl {
    private Map<String, List<Session>> sessionsMap;
    
    private static SessionRepositoryImpl sessionRepositoryImpl;
    
    public SessionRepositoryImpl(){
        sessionsMap = new HashMap<>();
    }
    
    public static SessionRepositoryImpl getInstance(){
        if(sessionRepositoryImpl == null){
            sessionRepositoryImpl = new SessionRepositoryImpl();
        }
        return sessionRepositoryImpl;
    }
    
    public void addSession(Session session, String identifier){
        if (!sessionsMap.containsKey(identifier)) {
            List<Session> auxList = new ArrayList<>();
            auxList.add(session);
            sessionsMap.put(identifier, auxList);
        }else {
            sessionsMap.get(identifier).add(session);
        }
    }
    
    public List<Session> getSessions(String identifier){
        return sessionsMap.get(identifier);
    }
    
    public void removeOneSession(Session session){
        for (String key : sessionsMap.keySet()) {
            if(sessionsMap.get(key).contains(session))
               sessionsMap.get(key).remove(session);
        }
    }
    
    public void removeSessionsList(String identifier){
        if (sessionsMap.containsKey(identifier)) {
            sessionsMap.remove(identifier);
        }
    }
}
