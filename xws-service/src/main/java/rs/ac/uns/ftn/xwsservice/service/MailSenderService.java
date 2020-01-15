package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.ConfirmationToken;

public interface MailSenderService {

    void sendRegistrationMail(ConfirmationToken token);
}
