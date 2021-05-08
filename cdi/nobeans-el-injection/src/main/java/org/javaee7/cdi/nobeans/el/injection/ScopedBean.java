package org.javaee7.cdi.nobeans.el.injection;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * @author Arun Gupta
 */
@Named
@RequestScoped
public class ScopedBean {
    public String sayHello() {
        return "Hello there!";
    }
}
