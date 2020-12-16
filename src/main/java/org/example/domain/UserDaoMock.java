package org.example.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class UserDaoMock extends Dao<UserEntity> {
    private long maxId = 0;

    private final Map<String, UserEntity> users;

    public UserDaoMock(){
        List<UserEntity> usersList = Arrays.asList(
                UserEntity.builder().id(nextId()).email("bartgeest@live.nl").password("Yolo").build(),
                UserEntity.builder().id(nextId()).email("kkcbart@live.nl").password("dikkekaas").build(),
                UserEntity.builder().id(nextId()).email("yaron_heerschap@hotmail.com").password("moeders").build()
        );

        this.users = usersList.stream().collect(toMap(UserEntity::getId, ue -> ue));
    }

    @Override
    public Collection<UserEntity> getAll() {
        return users.values();
    }

    public Collection<UserEntity> get(String q){
        Predicate<UserEntity> p1 = userEntity -> userEntity.getEmail() != null && userEntity.getEmail().contains(q);
        Predicate<UserEntity> p2 = userEntity -> userEntity.getPassword() != null && userEntity.getPassword().contains(q);

        return getAll().stream()
                .filter(p1.or(p2))
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity add(UserEntity entity) {
        if (entity.getId() == null) entity.setId(nextId());
        return this.users.put(entity.getId(), entity);
    }

    private String nextId() { return ++maxId + ""; }
}
