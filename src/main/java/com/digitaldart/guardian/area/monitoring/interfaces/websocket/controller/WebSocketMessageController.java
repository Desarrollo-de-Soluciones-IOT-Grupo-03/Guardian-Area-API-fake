package com.digitaldart.guardian.area.monitoring.interfaces.websocket.controller;

import com.digitaldart.guardian.area.monitoring.interfaces.websocket.handler.MyWebSocketHandler;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessageController {

    private final MyWebSocketHandler myWebSocketHandler;

    public WebSocketMessageController(MyWebSocketHandler myWebSocketHandler) {
        this.myWebSocketHandler = myWebSocketHandler;
    }

    // Ejemplo de método para enviar mensajes
    public void sendMessageToAllSessions(String message) {
        // Lógica para enviar un mensaje a todos los clientes conectados
    }
}