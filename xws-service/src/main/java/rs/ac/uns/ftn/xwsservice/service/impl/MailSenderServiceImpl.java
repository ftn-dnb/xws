package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.model.*;
import rs.ac.uns.ftn.xwsservice.repository.PublicationRepo;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PublicationRepo publicationRepo;

    @Autowired
    private UserRepository userRepository;

    private void sendMail(String title, String email, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setFrom("XWS-Tim05");
        message.setTo(email);
        message.setText(content);
        mailSender.send(message);
    }

    @Async
    @Override
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

    @Async
    @Override
    public void sendMail(User user, String publicationId, String coverLetterId) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Cover letter submitted");
        message.setFrom("XWS-Tim05");
        message.setTo(user.getEmail());

        message.setText("Links:\n" + "http://localhost:8080/api/publications/public/html/" + publicationId
                                   + "\nhttp://localhost:8080/api/publications/public/pdf/" + publicationId
                                   + "\n\nhttp://localhost:8080/api/coverLetters/public/html/" + coverLetterId
                                   + "\nhttp://localhost:8080/api/coverLetters/public/pdf/" + coverLetterId);

        mailSender.send(message);
    }

    @Override
    public void sendAccountActivatedMail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Account verified - XWS");
        message.setFrom("XWS-Tim05");
        message.setTo(user.getEmail());

        message.setText("Dear, " + user.getFirstName() + " " + user.getLastName() + ", your account has been verified.");

        mailSender.send(message);
    }

    @Async
    @Override
    public void sendRequestForReviewer(User user, PoslovniProces process) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Review request - XWS");
        message.setFrom("XWS-Tim05");
        message.setTo(user.getEmail());

        String publicationTitle = publicationRepo.findObjectById(process.getNaucniRadId()).getNaslovnaStrana().getNaslov().getValue();

        message.setText("You are requested to do a review for publication: " + publicationTitle +  ". Go to this page http://localhost:4200/review-requests " +
                "and accept or decline review request.");

        mailSender.send(message);
    }

    @Async
    @Override
    public void sendProcessChangeStatus(PoslovniProces process) throws Exception {
        NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
        String publicationTitle = publication.getNaslovnaStrana().getNaslov().getValue();
        String authorId = publication.getNaslovnaStrana().getAutori().getAutor().get(0).getId();

        User author = userRepository.findById(Long.valueOf(authorId)).get();
        this.sendProcessChangeStatusMail(author, publicationTitle, process.getStatusRada());

        for (CTRecenzent reviewer : process.getRecenzenti().getRecenzent()) {
            if (reviewer.getStatus().equals(EnumStatusRecenziranja.PRIHVACEN)) {
                User user = userRepository.findById(Long.valueOf(reviewer.getRecenzentID())).get();
                this.sendProcessChangeStatusMail(user, publicationTitle, process.getStatusRada());
            }
        }
    }

    private void sendProcessChangeStatusMail(User user, String title, EnumStatusRada status) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Publication review finished - XWS");
        message.setFrom("XWS-Tim05");
        message.setTo(user.getEmail());
        message.setText("Process for this publication is finished: " + title +  ". New status is " + status);

        mailSender.send(message);
    }

    @Async
    @Override
    public void sendReviewAddedToAuthor(PoslovniProces process) throws Exception {
        NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
        String publicationTitle = publication.getNaslovnaStrana().getNaslov().getValue();
        String authorId = publication.getNaslovnaStrana().getAutori().getAutor().get(0).getId();
        User author = userRepository.findById(Long.valueOf(authorId)).get();

        // TODO: Dodati prave linkove za pristup PDF i HTML verzijama recenzija

        this.sendMail("Review added for: " + publicationTitle, author.getEmail(),
                "New review has been added to your publication " + publicationTitle + "." +
                        " See HTML of review: http://localhost:8080/ and PDF version: http://localhost:8080/");
    }

    @Async
    @Override
    public void sendChangePhaseToReview(PoslovniProces process) throws Exception {
        NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
        String publicationTitle = publication.getNaslovnaStrana().getNaslov().getValue();

        for (CTRecenzent reviewer : process.getRecenzenti().getRecenzent()) {
            if (reviewer.getStatus().equals(EnumStatusRecenziranja.PRIHVACEN)) {
                User user = userRepository.findById(Long.valueOf(reviewer.getRecenzentID())).get();
                this.sendMail("Review phase for " + publicationTitle, user.getEmail(),
                        "Publication " + publicationTitle + " changed phase to review, so now you can " +
                                "add more reviews. Newest version of publication HTML" +
                                "http://localhost:8080/api/publications/public/html/" + process.getNaucniRadId() + "" +
                " and PDF here http://localhost:8080/api/publications/public/pdf/" + process.getNaucniRadId());
            }
        }
    }

    @Async
    @Override
    public void sendChangePhaseToRevise(PoslovniProces process) throws Exception {
        NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
        String publicationTitle = publication.getNaslovnaStrana().getNaslov().getValue();
        String authorId = publication.getNaslovnaStrana().getAutori().getAutor().get(0).getId();
        User author = userRepository.findById(Long.valueOf(authorId)).get();

        // TODO: dodati link za pristup mergovanim recenzijama

        this.sendMail("Revise phase for: " + publicationTitle, author.getEmail(),
                "Process phase for " + publicationTitle + " has been changed. Now you can add your revision." +
                        " See all reviews here: http://localhost:8080/");
    }

    @Async
    @Override
    public void sendReviseAddedToReviewers(PoslovniProces process) throws Exception {
        NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
        String publicationTitle = publication.getNaslovnaStrana().getNaslov().getValue();

        for (CTRecenzent reviewer : process.getRecenzenti().getRecenzent()) {
            if (reviewer.getStatus().equals(EnumStatusRecenziranja.PRIHVACEN)) {
                User user = userRepository.findById(Long.valueOf(reviewer.getRecenzentID())).get();
                this.sendMail("Revision added for " + publicationTitle, user.getEmail(),
                        "New revision has been added to publication you are reviewing. See HTML here " +
                                "http://localhost:8080/api/publications/public/html/" + process.getNaucniRadId() + "" +
                                " and PDF here http://localhost:8080/api/publications/public/pdf/" + process.getNaucniRadId());
            }
        }
    }
}
