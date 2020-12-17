package org.example.resources;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.example.domain.UserDao;
import org.example.domain.UserEntity;
import org.example.utils.PasswordUtils;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/user") //hhtp://localhost:9080/api/user
public class UserResource implements JsonResource {

    @Inject
    private UserDao userDao;

    @POST @Path("/login")
    public UserEntity login(UserEntity ue){
        try {
            //hash het password wat je binnen krijgt
            String hashedPass = PasswordUtils.digestPassword(ue.getPassword());
            //pak ue erbij van dao
            UserEntity userEntity = userDao.getLoginCredentials(ue.getEmail());

            //check of passwords gelijk zijn
            if (hashedPass.equals(userEntity.getPassword())){
                return userEntity;
            } else {
                throw new RuntimeException("Wachtwoorden zijn niet gelijk");
            }
        } catch (Exception e) {
            throw new NotAuthorizedException("Niet mogelijk om in te loggen");
        }
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
