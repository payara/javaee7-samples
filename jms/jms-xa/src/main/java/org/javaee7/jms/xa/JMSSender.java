package org.javaee7.jms.xa;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConnectionFactoryDefinition;
import jakarta.jms.JMSConnectionFactoryDefinitions;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.Queue;

@JMSDestinationDefinition(
    name = "java:app/jms/queue", 
    interfaceName = "jakarta.jms.Queue"
)
@JMSConnectionFactoryDefinitions(
    value = {
        // Will be selected via the NonXAConnectionFactoryProducer
        @JMSConnectionFactoryDefinition(
            name = "java:app/jms/nonXAconnectionFactory",
            transactional = false
        ),
        
        // Will be selected via the XAConnectionFactoryProducer
        @JMSConnectionFactoryDefinition(
            name = "java:app/jms/xaConnectionFactory"
        )
    }        
)
@Singleton
public class JMSSender {

    @Inject
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:app/jms/queue")
    private Queue queue;

    public void sendMessage(String payload) {
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(queue, payload);
        }
    }
}
