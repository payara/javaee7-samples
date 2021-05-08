package org.javaee7.cdi.alternatives.priority;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

/**
 * @author Arun Gupta
 * @author Radim Hanus
 */
@Priority(1000)
@Alternative
public class FancyGreeting implements Greeting {
    @Override
    public String greet(String name) {
        return "Nice to meet you, hello" + name;
    }
}
