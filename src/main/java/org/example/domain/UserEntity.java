package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NamedQuery(name = UserEntity.FIND_BY_LOGIN_PASSWORD, query = "select u from UserEntity u where u.email = :login and u.password = :password")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    public static final String FIND_BY_LOGIN_PASSWORD = "UserEntity.findByLoginAndPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(length = 256, nullable = false)
    private String password;

    private boolean akkoord;

    private boolean m_afhalen;
    private boolean t_afhalen;
    private boolean versturen;
    private boolean rembours;

    private String adres;
    private String postcode;
    private String woonplaats;

    //hier artikeldingen toevoegen
}
