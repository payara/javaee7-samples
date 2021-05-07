package org.javaee7.jms.batch;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;

/**
 * Create durable subscription upon deployment.
 *
 * Durable subscription needs unique subscription name and client id. Since setting
 * client id is not possible in Java EE environment, we define app-specific connection
 * factory with a client id.
 *
 * @author Patrik Dudits
 */
@Singleton
@Startup
public class SubscriptionCreator {

    @Resource(lookup = Resources.TOPIC)
    Topic topic;

    @Resource(lookup = Resources.CONNECTION_FACTORY)
    ConnectionFactory factory;

    /**
     * We create the subscription at soonest possible time after deployment so we
     * wouldn't miss any message
     */
    @PostConstruct
    void createSubscription() {
        try (JMSContext jms = factory.createContext()) { // <1> This is factory with clientId specified
            JMSConsumer consumer = jms.createDurableConsumer(topic, Resources.SUBSCRIPTION); // <2> creates durable subscription on the topic
            consumer.close();
        }
    }
}
