/** Copyright Payara Services Limited **/

package org.javaee7.jaxrs.simple.get;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * A very simple JAX-RS resource class that just returns the string "hi!"
 *
 * @author Arjan Tijms
 *
 */
@Path("/resource")
@Produces(TEXT_PLAIN)
public class Resource {

    @GET
    @Path("hi")
    public String hi() {
       return "hi!";
    }

}
