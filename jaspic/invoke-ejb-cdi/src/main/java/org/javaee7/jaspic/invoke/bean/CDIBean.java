package org.javaee7.jaspic.invoke.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class CDIBean {
    
    @Inject
    private HttpServletRequest request;

    public String getText() {
        return "Called from CDI";
    }
    
    public void setTextViaInjectedRequest() {
        request.setAttribute("text", "Called from CDI via injected request");
    }
    
}
