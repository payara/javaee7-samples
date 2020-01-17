/** Copyright Payara Services Limited **/
package org.javaee7.jaxrs.simple.methods;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.javaee7.jaxrs.simple.methods.filter.ExceptionResponse;
import org.javaee7.jaxrs.simple.methods.model.Resource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This sample tests one of the simplest possible JAX-RS resources; one that
 * only has a single method responding to a GET request and returning a (small)
 * string.
 *
 * @author Arjan Tijms
 */
@RunWith(Arquillian.class)
public class JaxRsMethodTest {

    @ArquillianResource
    private URL base;

    private URI resourceUri;

    private static Resource testResource;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive archive = create(WebArchive.class)
            .addPackages(true, JaxRsActivator.class.getPackage());

        System.out.println("************************************************************");
        System.out.println(archive.toString(true));
        System.out.println("************************************************************");

        return archive;
    }

    @Before
    public void constructResourceUri() throws MalformedURLException {
        resourceUri = URI.create(new URL(base, "rest/resource").toExternalForm());
    }

    @BeforeClass
    public static void constructResource() {
        testResource = new Resource(UUID.randomUUID().toString());
    }

    @Test
    @InSequence(1)
    public void when_POST_valid_object_expect_same_object_returned() {
        Response response = newClient()
            .target(resourceUri)
            .request(APPLICATION_JSON_TYPE)
            .post(entity(testResource, APPLICATION_JSON_TYPE));
        response.bufferEntity();

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Response: \n\n" + response.readEntity(String.class));
        System.out.println("-------------------------------------------------------------------------");

        assertEquals("The endpoint returned a non-successful status", 200, response.getStatus());
        assertEquals("The resource returned didn't match the resource posted", testResource, response.readEntity(Resource.class));
    }

    @Test
    @InSequence(2)
    public void when_POST_no_object_expect_mapped_exception() {
        Response response = newClient()
            .target(resourceUri)
            .request(APPLICATION_JSON_TYPE)
            .post(entity(null, APPLICATION_JSON_TYPE));
        response.bufferEntity();
        ExceptionResponse exception = response.readEntity(ExceptionResponse.class);

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Response: \n\n" + response.readEntity(String.class));
        System.out.println("-------------------------------------------------------------------------");

        assertEquals("The endpoint should return the defined 500 status", 500, response.getStatus());
        assertEquals("The returned exception message was wrong", "Cannot add an empty resource", exception.getMessage());
        assertEquals("The returned exception type was wrong", "IllegalArgumentException", exception.getType());
    }

    @Test
    @InSequence(3)
    public void when_GET_expect_array_of_objects() throws IOException {
        Response response = newClient()
            .target(resourceUri)
            .request(APPLICATION_JSON_TYPE)
            .get();
        response.bufferEntity();

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Response: \n\n" + response.readEntity(String.class));
        System.out.println("-------------------------------------------------------------------------");

        assertEquals("The endpoint returned a non-successful status", 200, response.getStatus());
        assertTrue("The posted resource was not returned in the GET body", Arrays.equals(response.readEntity(Resource[].class), new Resource[]{testResource}));
    }

}
