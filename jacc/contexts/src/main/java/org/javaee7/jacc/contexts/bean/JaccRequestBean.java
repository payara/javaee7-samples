package org.javaee7.jacc.contexts.bean;

import jakarta.ejb.Stateless;
import jakarta.security.jacc.PolicyContext;
import jakarta.security.jacc.PolicyContextException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * @author Arjan Tijms
 *
 */
@Stateless
public class JaccRequestBean {

    public HttpServletRequest getRequest() throws PolicyContextException {
        return (HttpServletRequest) PolicyContext.getContext("jakarta.servlet.http.HttpServletRequest");
    }

    public boolean hasAttribute() throws PolicyContextException {
        return "true".equals(getRequest().getAttribute("jaccTest"));
    }

    public boolean hasParameter() throws PolicyContextException {
        return "true".equals(getRequest().getParameter("jacc_test"));
    }
}
