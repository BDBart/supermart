package org.example.domain;

import org.example.utils.PasswordSender;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import static org.example.utils.PasswordUtils.generatePassword;
import static org.example.utils.PasswordUtils.digestPassword;

@Stateless
public class UserDao extends Dao<UserEntity> {

    public void checkCredentials(String login, String password) {
        TypedQuery<UserEntity> query = entityManager.createNamedQuery(UserEntity.FIND_BY_LOGIN_PASSWORD, UserEntity.class);
        query.setParameter("email", login);
        query.setParameter("password", password);
        UserEntity ue = query.getSingleResult();

        if (ue == null) throw new SecurityException("Invalid email or password");
    }
}
