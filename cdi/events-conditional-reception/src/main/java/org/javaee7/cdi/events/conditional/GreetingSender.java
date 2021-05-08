package org.javaee7.cdi.events.conditional;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

/**
 * @author Radim Hanus
 */
public class GreetingSender implements EventSender {
    @Inject
    private Event<String> event;

    @Override
    public void send(String message) {
        event.fire(message);
    }
}
