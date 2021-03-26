package ru.waveaccess.conference.service.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import ru.waveaccess.conference.service.interfaces.MailService;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

@Service
@EnableAsync
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(String to, String body) {
        MimeMessagePreparator preparator = mimeMessage -> {
            String html = "Verification <a href='http://localhost:8080/confirm?token=" + body + "'>link</a>";

            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setFrom(new InternetAddress("idzhalil@gmail.com"));
            mimeMessage.setSubject("Confirmation");
            mimeMessage.setContent(html, "text/html");
        };

        try {
            javaMailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
