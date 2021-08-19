/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.descorp.rpgdocs.controllers;

import com.descorp.rpgdocs.models.Message;
import enums.ConnectionType;
import java.io.IOException;
import java.util.List;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/dices", encoders = MessageEncoder.class,decoders = FrontMessageDecoder.class)
public class DicesEndPoint {
    
    private List<Session> sessions;
    private Session masterSession;
    
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        sessions.add(session);
        session.getBasicRemote().sendObject(new Message(ConnectionType.OPEN));
    }
    
    @OnMessage
    public void onMessage(Session session, FrontMessage message) throws EncodeException, IOException {

    }
    
        @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException, EncodeException {
    }    

    private void sendMessage(Message msg) throws EncodeException, IOException {
        for (Session session : sessions) {
            session.getBasicRemote().sendObject(msg);
        }
        masterSession.getBasicRemote().sendObject(msg);
    }
}
