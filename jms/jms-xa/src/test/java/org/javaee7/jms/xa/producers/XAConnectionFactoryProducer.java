package org.javaee7.jms.xa.producers;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;

public class XAConnectionFactoryProducer {

    @Produces
    @Resource(lookup = "java:app/jms/xaConnectionFactory")
    private ConnectionFactory connectionFactory;
}
