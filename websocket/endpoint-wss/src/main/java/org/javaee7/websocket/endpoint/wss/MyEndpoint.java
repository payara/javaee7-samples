package org.javaee7.websocket.endpoint.wss;

import jakarta.websocket.OnMessage;
import jakarta.websocket.server.ServerEndpoint;

/**
 * @author Arun Gupta
 */
@ServerEndpoint("/websocket")
public class MyEndpoint {

    @OnMessage
    public String echoText(String name) {
        return name;
    }
}
