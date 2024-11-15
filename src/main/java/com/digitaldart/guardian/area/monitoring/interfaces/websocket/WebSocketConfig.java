package com.digitaldart.guardian.area.monitoring.interfaces.websocket;

import com.digitaldart.guardian.area.monitoring.interfaces.websocket.handler.GpsWebSocketHandler;
import com.digitaldart.guardian.area.monitoring.interfaces.websocket.handler.MyWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MyWebSocketHandler webSocketHandler;
    private final GpsWebSocketHandler gpsWebSocketHandler;

    public WebSocketConfig(MyWebSocketHandler webSocketHandler, GpsWebSocketHandler gpsWebSocketHandler) {
        this.webSocketHandler = webSocketHandler;
        this.gpsWebSocketHandler = gpsWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/health-measures-stream").setAllowedOrigins("*");
        registry.addHandler(gpsWebSocketHandler, "/location-stream").setAllowedOrigins("*");
    }
}