package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.ConfirmationToken;
import rs.ac.uns.ftn.xwsservice.model.User;

public interface MailSenderService {

    void sendRegistrationMail(ConfirmationToken token);
    void sendAccountActivatedMail(User user);
}
