package org.javaee7.websocket.chat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

/**
 * @author Arun Gupta
 */
@ClientEndpoint
public class ChatClientEndpoint1 {
    public static String TEXT = "Client1 joins";
    public static CountDownLatch latch;
    public static String response;

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText(TEXT);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @OnMessage
    public void processMessage(String message) {
        response = message;
        latch.countDown();
    }
}
