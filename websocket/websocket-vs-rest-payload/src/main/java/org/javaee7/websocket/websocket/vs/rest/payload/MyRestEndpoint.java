package org.javaee7.websocket.websocket.vs.rest.payload;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Arun Gupta
 */
@Path("rest")
public class MyRestEndpoint {

    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    public String getXml(String payload) {
        return payload;
    }
}
