package org.javaee7.cdi.alternatives.priority;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;

/**
 * @author Radim Hanus
 */
@Priority(2000)
@Alternative
public class PriorityGreeting implements Greeting {
    @Override
    public String greet(String name) {
        return "Hey " + name + " I should be selected since I've got the highest priority !";
    }
}
