package org.javaee7.jaxrs.fileupload;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Xavier Coulon
 */
@Path("/endpoint")
public class MyResource {

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postOctetStream(InputStream content) {
        try (Reader reader = new InputStreamReader(content)) {
            int totalsize = 0;
            int count = 0;
            final char[] buffer = new char[256];
            while ((count = reader.read(buffer)) != -1) {
                totalsize += count;
            }
            return Response.ok(totalsize).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/upload2")
    @Consumes({ MediaType.APPLICATION_OCTET_STREAM, "image/png" })
    @Produces(MediaType.TEXT_PLAIN)
    public Response postImageFile(File file) {
        try (Reader reader = new FileReader(file)) {
            int totalsize = 0;
            int count = 0;
            final char[] buffer = new char[256];
            while ((count = reader.read(buffer)) != -1) {
                totalsize += count;
            }
            return Response.ok(totalsize).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
