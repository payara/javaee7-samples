package org.javaee7.jaspic.invoke.bean;

import jakarta.ejb.Stateless;

@Stateless
public class EJBBean {

    public String getText() {
        return "Called from EJB";
    }
    
}
