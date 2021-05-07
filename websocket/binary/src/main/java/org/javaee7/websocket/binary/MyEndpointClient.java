/**
 *
 */
package org.javaee7.websocket.binary;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

/**
 * @author Nikolaos Ballas
 *
 */
@ClientEndpoint
public class MyEndpointClient {
    public static CountDownLatch latch;
    public static byte[] response;

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendBinary(ByteBuffer.wrap("Hello World!".getBytes()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @OnMessage
    public void processMessage(byte[] message) {
        MyEndpointClient.response = message;
        latch.countDown();
    }
}
