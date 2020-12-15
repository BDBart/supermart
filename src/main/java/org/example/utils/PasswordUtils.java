package org.example.utils;

import java.security.MessageDigest;
import java.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PasswordUtils {

    //Hasht wachtwoorden met de SHA-256 algoritme
    public static String digestPassword(String pw){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes(UTF_8));
            return new String(Base64.getEncoder().encode(md.digest()));

        } catch (Exception e) {
            throw new RuntimeException("Password couldn't be encoded...", e);
        }
    }

    //genereert een willekeurige String(wachtwoord)
    public static String generatePassword(){
        String generatedPassword = RandomStringUtils.random(10, true, true);
        return generatedPassword;
    }


}
