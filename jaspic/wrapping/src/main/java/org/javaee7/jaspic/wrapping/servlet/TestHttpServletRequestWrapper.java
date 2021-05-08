package org.javaee7.jaspic.wrapping.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * 
 * @author Arjan Tijms
 * 
 */
public class TestHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public TestHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Object getAttribute(String name) {

        if ("isWrapped".equals(name)) {
            return Boolean.TRUE;
        }

        return super.getAttribute(name);
    }

}
