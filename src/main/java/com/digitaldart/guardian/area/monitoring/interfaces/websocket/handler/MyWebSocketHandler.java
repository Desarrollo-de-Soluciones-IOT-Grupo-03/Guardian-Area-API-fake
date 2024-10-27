package com.digitaldart.guardian.area.monitoring.interfaces.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Set para guardar todas las sesiones activas
    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session); // Agrega la sesión al conjunto
        System.out.println("Nueva conexión: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Mensaje recibido: " + message.getPayload() + " " +session.getUri());
        broadcastMessage(message.getPayload(), session);
    }

    // Método para enviar un mensaje a todas las sesiones
    private void broadcastMessage(String message, WebSocketSession senderSession) {
        synchronized (sessions) {
            sessions.forEach(session -> {
                if (!session.equals(senderSession)) { // Excluye al remitente
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session); // Elimina la sesión cuando el cliente se desconecta
        System.out.println("Conexión cerrada: " + session.getId());
    }
}