package org.example.utils;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class PasswordSender {
    private static final String USER_EMAIL = "traineeship.martkplaats.no.reply@gmai.com";
    private static final String EMAIL_PASSWORD = "JeMoeder@69";

    public static void sendPasswordToNewUser(String to, String sub, String content){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.port", "465");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(USER_EMAIL, EMAIL_PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(content);
            Transport.send(message);
            System.out.println("Email has been sent to new user");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
