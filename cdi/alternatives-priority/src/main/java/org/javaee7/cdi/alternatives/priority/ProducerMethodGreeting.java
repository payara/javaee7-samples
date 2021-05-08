package org.javaee7.cdi.alternatives.priority;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;

/**
 * @author Radim Hanus
 */
@Priority(3000)
public class ProducerMethodGreeting {

    @Produces
    @Alternative
    public Greeting getGreeting() {
        return new SimpleGreeting();
    }
}
