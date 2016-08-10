package com.napkinstudio.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by User1 on 03.08.2016.
 */

@Service("mailService")
public class MailManager {

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(Object object) {

        String message = (String) object;

        MimeMessagePreparator preparator = getMessagePreparator(message);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final String message) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("customerserivces@yourshop.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress("alexkhomenko@yahoo.com"));
                mimeMessage.setText(message);
                mimeMessage.setSubject("Lesyk Best!");
            }
        };
        return preparator;
    }
}

