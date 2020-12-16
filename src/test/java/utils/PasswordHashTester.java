package utils;

import org.example.utils.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordHashTester {
    private final String testPassword = "testPassword";

    @Test
    void testForHashing(){
        String hashedPassword = PasswordUtils.digestPassword(testPassword);

        assertThat(hashedPassword).isEqualTo(PasswordUtils.digestPassword(testPassword));
    }

}
