/** Copyright Payara Services Limited **/
package org.javaee7.cdi.decorators.builtin;

import java.io.Serializable;

import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

@Decorator
@Priority(100)
public abstract class RequestDecorator implements HttpServletRequest, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @Delegate
    private HttpServletRequest request;

    @Override
    public String getParameter(String name) {

        if ("decorated".equals(name)) {
            return "true";
        }

        return request.getParameter(name);
    }

}
