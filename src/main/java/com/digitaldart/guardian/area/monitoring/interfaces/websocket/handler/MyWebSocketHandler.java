package com.digitaldart.guardian.area.monitoring.interfaces.websocket.handler;

import com.digitaldart.guardian.area.monitoring.domain.services.HealthMeasureCommandService;
import com.digitaldart.guardian.area.monitoring.interfaces.websocket.resource.CreateHealthMeasureResource;
import com.digitaldart.guardian.area.monitoring.interfaces.websocket.transform.CreateHealthMeasureCommandFromResourceAssembler;
import com.digitaldart.guardian.area.monitoring.interfaces.websocket.transform.HealthMeasureResourceFromEntityAssembler;
import com.digitaldart.guardian.area.shared.domain.exceptions.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final HealthMeasureCommandService healthMeasureCommandService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Mapa para almacenar las sesiones agrupadas por sala
    private final Map<String, Set<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    public MyWebSocketHandler(HealthMeasureCommandService healthMeasureCommandService) {
        this.healthMeasureCommandService = healthMeasureCommandService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String roomName = getRoomNameFromSession(session);
        System.out.println(roomName);
        roomSessions.putIfAbsent(roomName, Collections.synchronizedSet(new HashSet<>()));
        roomSessions.get(roomName).add(session);

        System.out.println("Nueva conexión en sala: " + roomName + ", sesión: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        var createHealthMeasureResource = objectMapper.readValue(message.getPayload(), CreateHealthMeasureResource.class);
        var apiKey = getRoomNameFromSession(session);
        var createHealthMeasureCommand = CreateHealthMeasureCommandFromResourceAssembler.toCommandFromResource(apiKey, createHealthMeasureResource);
        var healthMeasure = healthMeasureCommandService.handle(createHealthMeasureCommand);
        if (healthMeasure.isEmpty()) {
            broadcastMessageToRoom(apiKey, "Failed to create", session);
            throw new ValidationException("");
        }
        var healthMeasureResource = HealthMeasureResourceFromEntityAssembler.toResourceFromEntity(healthMeasure.get());
        var healthMeasureResourceString = objectMapper.writeValueAsString(healthMeasureResource);
        broadcastMessageToRoom(apiKey, healthMeasureResourceString, session);
    }

    // Método para enviar un mensaje a todas las sesiones
    private void broadcastMessageToRoom(String roomName, String message, WebSocketSession senderSession) {
        Set<WebSocketSession> sessions = roomSessions.get(roomName);
        if (sessions != null) {
            synchronized (sessions) {
                sessions.forEach(session -> {
                    if (!session.equals(senderSession)) { // Excluir al remitente
                        try {
                            session.sendMessage(new TextMessage(message));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String roomName = getRoomNameFromSession(session);
        Set<WebSocketSession> sessions = roomSessions.get(roomName);
        if (sessions != null) {
            sessions.remove(session);
            System.out.println("Conexión cerrada en sala: " + roomName + ", sesión: " + session.getId());

            // Si la sala queda vacía, la eliminamos
            if (sessions.isEmpty()) {
                roomSessions.remove(roomName);
            }
        }
    }

    private String getRoomNameFromSession(WebSocketSession session) {
        // Aquí obtén el nombre de la sala del cliente, por ejemplo desde query params
        // Ejemplo: http://localhost:8080/chat?room=room1
        String query = session.getUri().getQuery();
        return query != null && query.startsWith("room=") ? query.substring(5) : "default";
    }
}