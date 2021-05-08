package org.javaee7.cdi.nobeans.el.injection.flowscoped;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * @author Arun Gupta
 */
@Named
@RequestScoped
public class FlowScopedBean {
    public String sayHello() {
        return "Hello there!";
    }
}
