/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.sockets;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;


/**
 *
 * @author arthurandrade
 */
@ServerEndpoint("/chat/{boardnumber}")
public class ChatSocket implements Serializable{
    
    private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());
    
    public static void sendAll(String text, String roomNumber) {
        synchronized (sessions) {
            sessions.entrySet().forEach(entry -> {
                Session s = entry.getValue();
                if (s.isOpen() && s.getUserProperties().get("boardnumber").equals(roomNumber)) {
                    entry.getValue().getAsyncRemote().sendText(text);
                }
            });
        }
    }
    
    @OnOpen
    public void onConnectionOpen(final Session session, @PathParam("boardnumber") final String roomnumber) {
        session.getUserProperties().put("boardnumber", roomnumber);
        sessions.put(String.valueOf(session.getId()), session);
    }


    @OnClose
    public void onClose(Session session) {
        sessions.remove(session.getId());
    }
    
    
    @OnMessage
    public void onMessage(String message, Session session) throws EncodeException, IOException {
        String roomNumber = (String) session.getUserProperties().get("boardnumber");
        sendAll(message, roomNumber);
    }
    
}
