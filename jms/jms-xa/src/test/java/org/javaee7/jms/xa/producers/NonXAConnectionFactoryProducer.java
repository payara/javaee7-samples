package org.javaee7.jms.xa.producers;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;

public class NonXAConnectionFactoryProducer {

    @Produces
    @Resource(lookup = "java:app/jms/nonXAconnectionFactory")
    private ConnectionFactory connectionFactory;
}
