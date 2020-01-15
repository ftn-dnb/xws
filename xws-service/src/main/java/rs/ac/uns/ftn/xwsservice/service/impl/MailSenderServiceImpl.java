package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.model.ConfirmationToken;
import rs.ac.uns.ftn.xwsservice.service.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendRegistrationMail(ConfirmationToken token) {
        if(token.getUser().getEmail() == null)
            return;

        if(token.getToken() == null)
            return;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Account verification - XWS");
        message.setFrom("XWS-Tim05");
        message.setTo(token.getUser().getEmail());

        message.setText("Go to this page to activate your account http://localhost:4200/verify?token=" + token.getToken());

        mailSender.send(message);
    }
}
