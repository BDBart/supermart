package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = UserEntity.FIND_BY_LOGIN_PASSWORD, query = "select u from UserEntity u where u.email = :login and u.password = :password")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    public static final String FIND_BY_LOGIN_PASSWORD = "UserEntity.findByLoginAndPassword";

    @Id
    @GeneratedValue
    private long id;

    private String email;
    private String password;
}
