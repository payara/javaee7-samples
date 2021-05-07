package org.javaee7.jaspic.dispatching.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class MyBean {
    
    @Inject
    private HttpServletRequest request;

    public String getText() {
        return "Called from CDI\n";
    }
    
    public String getServletPath() {
        return request.getServletPath();
    }
    
}
