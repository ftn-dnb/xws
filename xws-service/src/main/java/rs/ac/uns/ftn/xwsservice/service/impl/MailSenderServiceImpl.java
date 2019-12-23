package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.service.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     *  Example of sending an email
     */
    @Async
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Mail subject");
        message.setFrom("From me");
        message.setTo("user@mail.com");

        message.setText("This is text");

        mailSender.send(message);
    }
}
