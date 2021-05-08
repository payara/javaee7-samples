// Copyright [2019] [Payara Foundation and/or its affiliates]
package org.javaee7.jaxrs.ejb.lookup.iface.jar;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

public interface HelloEndpoint {

    @POST
    @Path("/logHello")
    void sayHelloToLog();

}
