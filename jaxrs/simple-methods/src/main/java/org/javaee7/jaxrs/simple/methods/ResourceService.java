/** Copyright Payara Services Limited **/

package org.javaee7.jaxrs.simple.methods;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.javaee7.jaxrs.simple.methods.model.Resource;
import org.javaee7.jaxrs.simple.methods.model.ResourceDAO;

/**
 * A very simple JAX-RS resource class that just returns the string "hi!"
 *
 * @author Arjan Tijms
 *
 */
@Path("/resource")
@RequestScoped
@Produces(APPLICATION_JSON)
public class ResourceService {

    @Inject
    private ResourceDAO resources;

    @GET
    public List<Resource> getResources() {
       return resources.getAll();
    }

    @POST
    public Resource addResource(Resource resource) {
        if (resource != null) {
            if (resources.add(resource)) {
                return resource;
            }
            throw new IllegalStateException("Unable to add resource");
        }
        throw new IllegalArgumentException("Cannot add an empty resource");
    }

    @DELETE
    public Resource deleteResource(Resource resource) {
        if (resource != null) {
            if (resources.remove(resource)) {
                return resource;
            }
            throw new IllegalStateException("Unable to find the resource to delete");
        }
        throw new IllegalArgumentException("Cannot add an empty resource");
    }

}
