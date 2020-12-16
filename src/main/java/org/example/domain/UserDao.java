package org.example.domain;

import org.example.utils.PasswordSender;
import org.example.utils.PasswordUtils;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
public class UserDao extends Dao<UserEntity> {

    public void checkLoginCredentials(String login, String password) {
        TypedQuery<UserEntity> query = entityManager.createNamedQuery(UserEntity.FIND_BY_LOGIN_PASSWORD, UserEntity.class);
        query.setParameter("email", login);
        query.setParameter("password", PasswordUtils.digestPassword(password));
        UserEntity ue = query.getSingleResult();

        if (ue == null) throw new SecurityException("Invalid email or password");
    }

    public boolean findByEmail(String email){
        TypedQuery<Long> query = entityManager.createQuery("select count(ue) from UserEntity ue where ue.email like :firstarg", Long.class);
        query.setParameter("firstarg", "%" + email + "%");
        Long count = query.getSingleResult();
        return (!count.equals(0L));
    }

    @Override
    public UserEntity add(UserEntity entity) {
        // wachtwoord hashen en setten en opslaan
        entity.setPassword(PasswordUtils.digestPassword(entity.getPassword()));
        return super.add(entity);
    }
}
