package domain;

import org.example.domain.UserDaoMock;
import org.example.domain.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    private final UserDaoMock target = new UserDaoMock();

    @Test
    void addTest(){
        int before = target.getAll().size();
        target.add(new UserEntity());
        int after = target.getAll().size();

        assertThat(after - before).isEqualTo(1);
    }

    @Test
    void getTest(){
        Collection<UserEntity> m = target.get("b");
        System.out.println(m.size());
    }
}
