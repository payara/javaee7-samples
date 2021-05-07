package org.javaee7.jms.batch;

import jakarta.jms.JMSConnectionFactoryDefinition;
import jakarta.jms.JMSDestinationDefinition;

/**
 * @author Patrik Dudits
 */
@JMSDestinationDefinition(
    name = Resources.TOPIC,
    resourceAdapter = "jmsra",
    interfaceName = "jakarta.jms.Topic",
    destinationName = "batch.topic",
    description = "Batch processing topic")
@JMSConnectionFactoryDefinition( // <1> WildFly appears to require user and password to be set for connection factories
    name = Resources.CONNECTION_FACTORY,
    resourceAdapter = "jmsra",
    clientId = "batchJob", // <2> It is not allowed to call +setClientId+ on +Connection+ or +JMSContext+ in Java EE container.
    description = "Connection factory with clientId of the durable subscription")
public class Resources {
    public static final String SUBSCRIPTION = "BatchJob"; // <3> Durable consumer is uniquely identified with its +clientId+ and +subscriptionName+.
    public static final String TOPIC = "java:app/batch/topic";
    public static final String CONNECTION_FACTORY = "java:app/batch/factory";
}
