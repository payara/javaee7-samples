package org.javaee7.jaspic.wrapping.servlet;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @author Arjan Tijms
 * 
 */
public class TestHttpServletResponseWrapper extends HttpServletResponseWrapper {

    public TestHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public String getHeader(String name) {

        if ("isWrapped".equals(name)) {
            return "true";
        }

        return super.getHeader(name);
    }

}
