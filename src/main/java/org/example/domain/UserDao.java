package org.example.domain;

import org.example.utils.PasswordSender;
import org.example.utils.PasswordUtils;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@Stateless
public class UserDao extends Dao<UserEntity> {

    public UserEntity getLoginCredentials(String login) {
        TypedQuery<UserEntity> query = entityManager.createQuery("select ue from UserEntity ue where ue.email like :firstarg", UserEntity.class);
        query.setParameter("firstarg", "%" + login + "%");
        UserEntity userEntity = query.getSingleResult();
        return userEntity;
    }

    @Override
    public UserEntity add(UserEntity entity) {
        // wachtwoord hashen en setten en opslaan
        entity.setPassword(PasswordUtils.digestPassword(entity.getPassword()));
        return super.add(entity);
    }

    public boolean findByEmail(String email){
        TypedQuery<Long> query = entityManager.createQuery("select count(ue) from UserEntity ue where ue.email like :firstarg", Long.class);
        query.setParameter("firstarg", "%" + email + "%");
        Long count = query.getSingleResult();
        return (!count.equals(0L) );
    }
}
