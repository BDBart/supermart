package org.example.resources;

import org.example.domain.UserDao;
import org.example.domain.UserEntity;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/user")
public class UserResource {

    @Inject
    private UserDao userDao;

    @POST
    public UserEntity post(UserEntity ue){
        if (userDao.add(ue) != null){
            return ue;
        } else throw new RuntimeException("User could not be created...");
    }
}
