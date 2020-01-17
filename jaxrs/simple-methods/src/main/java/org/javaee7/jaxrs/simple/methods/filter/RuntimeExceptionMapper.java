/** Copyright Payara Services Limited **/
package org.javaee7.jaxrs.simple.methods.filter;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response
            .status(500)
            .entity(new ExceptionResponse(exception))
            .build();
    }

}