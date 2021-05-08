package org.javaee7.jms.send.receive.simple.appmanaged;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Queue;

import org.javaee7.jms.send.receive.Resources;

/**
 * Synchronous message sending with app-managed JMSContext. JMSContext can be
 * used with try-with-resources construct.
 *
 * @author Arun Gupta
 */
@Stateless
public class MessageSenderAppManaged {

    @Resource
    private ConnectionFactory factory;

    @Resource(mappedName = Resources.SYNC_APP_MANAGED_QUEUE)
    Queue myQueue;

    /**
     * Send a message to the JMS queue.
     *
     * @param message the contents of the message.
     * @throws JMSRuntimeException if an error occurs in accessing the queue.
     */
    public void sendMessage(String message) throws JMSRuntimeException {
        try (JMSContext context = factory.createContext()) {
            context.createProducer().send(myQueue, message);
        }
    }
}
