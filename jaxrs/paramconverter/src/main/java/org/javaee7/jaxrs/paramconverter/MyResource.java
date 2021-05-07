package org.javaee7.jaxrs.paramconverter;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Arun Gupta
 * @author Xavier coulon
 */
@Path("/endpoint")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWithQuery(@DefaultValue("bar") @QueryParam("search") MyBean myBean) {
        return myBean.getValue();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByPath(@PathParam("id") MyBean myBean) {
        return myBean.getValue();
    }
}
