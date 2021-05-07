package org.javaee7.jca.connector.simple.connector;

import java.util.logging.Logger;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ActivationSpec;
import jakarta.resource.spi.BootstrapContext;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.ResourceAdapterInternalException;
import jakarta.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

/**
 *
 * @author arungup
 */
public class MyResoureAdapter implements ResourceAdapter {

    private static final Logger LOGGER = Logger.getLogger("MyResourceAdapter");

    @Override
    public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {
        LOGGER.info("start");
    }

    @Override
    public void stop() {
        LOGGER.info("stop");
    }

    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) throws ResourceException {
        LOGGER.info("endpointActivation");
    }

    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) {
        LOGGER.info("endpointDeactivation");
    }

    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException {
        return null;
    }

}
