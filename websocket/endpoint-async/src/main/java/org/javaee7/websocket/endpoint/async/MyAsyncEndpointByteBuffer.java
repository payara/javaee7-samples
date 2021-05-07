package org.javaee7.websocket.endpoint.async;

import java.nio.ByteBuffer;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * @author Jacek Jackowiak
 */
@ServerEndpoint("/bytebuffer")
public class MyAsyncEndpointByteBuffer {

    @OnMessage
    public void echoByteBuffer(ByteBuffer data, Session session) {
        session.getAsyncRemote().sendBinary(data);
    }
}
