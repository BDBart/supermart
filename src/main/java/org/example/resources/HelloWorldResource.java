package org.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class HelloWorldResource {
    @GET
    public Response getHelloWord(){
        return Response
                .ok()
                .entity("Hello World!")
                .build();
    }
}
