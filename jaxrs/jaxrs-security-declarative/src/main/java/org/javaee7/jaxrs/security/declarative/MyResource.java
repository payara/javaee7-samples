package org.javaee7.jaxrs.security.declarative;

import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

/**
 * @author Arun Gupta
 */
@RequestScoped
@Path("myresource")
@Produces(TEXT_PLAIN)
public class MyResource {

    @GET
    public String get() {
        return "get";
    }

    @GET
    @Path("{id}")
    public String getPerson(@PathParam("id") int id) {
        return "get" + id;
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public String addToList(@FormParam("name") String name) {
        return "post " + name;
    }

    @PUT
    public void putToList() {
        System.out.println("put invoked");
    }

    @DELETE
    public void delete() {
        System.out.println("delete invoked");
    }
}
