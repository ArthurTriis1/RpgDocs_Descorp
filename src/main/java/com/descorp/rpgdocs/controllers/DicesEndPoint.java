/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Message;
import com.descorp.rpgdocs.repositoriesImpl.SessionRepositoryImpl;
import enums.ConnectionType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/dices", encoders = MessageEncoder.class,decoders = FrontMessageDecoder.class)
public class DicesEndPoint {
    private SessionRepositoryImpl sessionsRep = SessionRepositoryImpl.getInstance();
    private int dice;
    List<Session> sessions;
    
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        sessions.add(session);
        session.getBasicRemote().sendObject(new Message(ConnectionType.OPEN));
    }
    
    @OnMessage
    public void onMessage(Session session, FrontMessage message) throws EncodeException, IOException {
        if (message.getConnectionType().equals(ConnectionType.OPEN)){
            sessionsRep.addSession(session, message.getidentifier());
            sessions.remove(session);
        }
        else if (message.getConnectionType().equals(ConnectionType.MESSAGE)){
            Random r = new Random();
            dice = r.nextInt(6) + 1;
            sendMessage(message.getidentifier(), new Message(ConnectionType.MESSAGE, dice));
        }
    }
    
        @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException, EncodeException {
        sessionsRep.removeOneSession(session);
    }    

    private void sendMessage(String identifier, Message msg) throws EncodeException, IOException {
        for (Session session : sessionsRep.getSessions(identifier)) {
            session.getBasicRemote().sendObject(msg);
        }
    }
}
