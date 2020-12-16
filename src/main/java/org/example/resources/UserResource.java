package org.example.resources;

import jdk.nashorn.internal.objects.annotations.Getter;
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

//    @GET @Path("/login")
//    public

    @POST @Path("/register") //http://localhost:9080/api/user/register
    public UserEntity post(UserEntity ue){
        try {
            if (!userDao.findByEmail(ue.getEmail())){
                userDao.add(ue);
                return ue;
            } else {
                throw new RuntimeException("Account met email: " + ue.getEmail() + " bestaat al");
            }

        } catch (RuntimeException e){
            throw new RuntimeException("POST methode werd niet naar behoren uitgevoerd...");
        }
    }
}
