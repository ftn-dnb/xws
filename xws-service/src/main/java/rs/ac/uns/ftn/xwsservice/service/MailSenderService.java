package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.ConfirmationToken;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
import rs.ac.uns.ftn.xwsservice.model.User;

public interface MailSenderService {

    void sendRegistrationMail(ConfirmationToken token);
    void sendAccountActivatedMail(User user);
    void sendRequestForReviewer(User user, PoslovniProces process) throws Exception;
    void sendProcessChangeStatus(PoslovniProces process) throws Exception;
    void sendReviewAddedToAuthor(PoslovniProces process) throws Exception;
    void sendChangePhaseToReview(PoslovniProces process) throws Exception;
    void sendChangePhaseToRevise(PoslovniProces process) throws Exception;
    void sendReviseAddedToReviewers(PoslovniProces process) throws Exception;
}
