// Copyright [2019] [Payara Foundation and/or its affiliates]
package org.javaee7.jaxrs.ejb.lookup.iface.war;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.javaee7.jaxrs.ejb.lookup.iface.jar.HelloEndpoint;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Path("iface-in-library")
public class HelloBean implements HelloEndpoint {

    @Override
    public void sayHelloToLog() {
        System.out.println("HELLO!");
    }
}
