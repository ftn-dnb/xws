package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.UserDTO;
import rs.ac.uns.ftn.xwsservice.exception.ApiRequestException;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.*;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.ReviewRepository;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.*;
import rs.ac.uns.ftn.xwsservice.utils.ReviewIdUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UnmarshallerService unmarshallerService;

    @Autowired
    private DOMParserImpl domParser;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private XSLTService xsltService;

    @Autowired
    private XSLFOService xslfoService;

    @Value("${xsd.path.review}")
    private String reviewXmlSchemaPath;

    @Value("${xslfo.path.review}")
    private String xslfoTransformationPath;

    @Value("${xslt.path.review}")
    private String xsltTransformationPath;

    @Value("${xslfo.path.review.merged}")
    private String xslfoTransformationPathMerged;

    @Value("${xslt.path.review.merged}")
    private String xsltTransformationPathMerged;

    @Value("${xslt.path.output-folder.reviews}")
    private String xsltReviewOutputFolderPath;

    @Value("${xslfo.path.output-folder.reviews}")
    private String xslfoReviewOutputFolderPath;

    @Value("${xslt.path.output-folder.reviews.merged}")
    private String xsltReviewOutputFolderPathMerged;

    @Value("${xslfo.path.output-folder.reviews.merged}")
    private String xslfoReviewOutputFolderPathMerged;


    @Override
    public String addReview(String xmlData, String processId) throws Exception {
        Document document = domParser.isXmlDataValid(xmlData, reviewXmlSchemaPath);

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String reviewId = UUID.randomUUID().toString();
        String updatedXmlData = ReviewIdUtil.addReviewId(xmlData, reviewId);
        String id = reviewRepository.save(updatedXmlData, reviewId);
        PoslovniProces proces = businessProcessRepository.findObjectById(processId);

        if (!proces.getStatusRada().equals(EnumStatusRada.U_PROCESU)) {
            throw new ApiRequestException("This publication is no longer in process.");
        }

        if (!proces.getFaza().equals(EnumFaza.ZA_RECENZIJU)) {
            throw new ApiRequestException("This publication is not in 'for review' phase.");
        }

        CTRecenzent chosen = null;
        for (CTRecenzent recenzent : proces.getRecenzenti().getRecenzent()) {
            if (recenzent.getRecenzentID().equals(loggedUser.getId().toString())) {
                chosen = recenzent;
            }
        }

        if (chosen == null) {
            throw new ApiRequestException("You can't review this publication");
        }

        chosen.getRecenzije().getRecenzijaID().add(id);

        businessProcessRepository.saveObject(proces);
        mailSenderService.sendReviewAddedToAuthor(proces);

        this.createTransformationsForReview(xmlData, reviewId);
        this.createTransformationsForMergedReviews(processId);

        return id;
    }

    private void createTransformationsForReview(String xmlData, String reviewId) throws Exception {
        String xsltOutputFilePath = xsltReviewOutputFolderPath + reviewId;
        xsltService.transform(xmlData, xsltTransformationPath, xsltOutputFilePath);

        String xslfoOutputFilePath = xslfoReviewOutputFolderPath + reviewId;
        xslfoService.transform(xmlData, xslfoTransformationPath, xslfoOutputFilePath);
    }

    private void createTransformationsForMergedReviews(String processId) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(processId);

        StringBuilder xmlData = new StringBuilder();
        xmlData.append("<Recenzije>");

        for (CTRecenzent reviewer : process.getRecenzenti().getRecenzent()) {
            if (reviewer.getRecenzije() == null)
                continue;;

            for (String reviewId : reviewer.getRecenzije().getRecenzijaID()) {
                String reviewXmlData = reviewRepository.findById(reviewId);

                if (reviewXmlData == null)
                    continue;

                xmlData.append(reviewXmlData);
            }
        }

        xmlData.append("</Recenzije>");

        String xsltOutputFilePath = xsltReviewOutputFolderPathMerged + processId;
        xsltService.transform(xmlData.toString(), xsltTransformationPathMerged, xsltOutputFilePath);

        String xslfoOutputFilePath = xslfoReviewOutputFolderPathMerged + processId;
        xslfoService.transform(xmlData.toString(), xslfoTransformationPathMerged, xslfoOutputFilePath);
    }

    @Override
    public String getReviewById(String id) throws Exception {
        String xml = reviewRepository.findById(id);
        if (xml == null) {
            throw new ResourceNotFoundException("Publication with ID " + id + " doesn't exist.");
        }
        return xml;
    }

    @Override
    public List<ReviewDTO> getReviewsByProcessId(String id) throws Exception {
        ArrayList<ReviewDTO> reviews = new ArrayList<>();

        PoslovniProces process = businessProcessRepository.findObjectById(id);
        for (CTRecenzent recenzent : process.getRecenzenti().getRecenzent()) {
            User user = userRepository.findById(Long.parseLong(recenzent.getRecenzentID())).get();
            for (String idRecenzija : recenzent.getRecenzije().getRecenzijaID()) {
                Recenzija recenzija = reviewRepository.findObjectById(idRecenzija);
                ReviewDTO dto = new ReviewDTO();
                dto.setUser(new UserDTO(user));
                dto.setPreporuka(recenzija.getPreporuka());
                dto.setId(recenzija.getId());
                dto.setKomentari(recenzija.getKomentar());
                reviews.add(dto);
            }
        }

        return reviews;
    }

    @Override
    public Recenzija getReviewObjectById(String id) throws Exception {
        String xmlData = this.getReviewById(id);
        Recenzija recenzija = (Recenzija) unmarshallerService.unmarshal(xmlData);
        return recenzija;
    }
}
