package org.example.resources;

import org.example.domain.UserDao;
import org.example.domain.UserEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/user") //hhtp://localhost:9080/api/user
public class UserResource implements JsonResource {

    @Inject
    private UserDao userDao;

    //hhtp://localhost:9080/api/user/register WORKS
    @GET @Path("/register")
    public Response getHelloWord(){
        return Response
                .ok()
                .entity("Hello World!")
                .build();
    }

    @POST @Path("/register") //http://localhost:9080/api/user/register
    public UserEntity post(UserEntity ue){
        try {
            userDao.add(ue);
            return ue;
        } catch (RuntimeException e){
            throw new RuntimeException("POST methode werd niet naar behoren uitgevoerd...");
        }
    }
}
