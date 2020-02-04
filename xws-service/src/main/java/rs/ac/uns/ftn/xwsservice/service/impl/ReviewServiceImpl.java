package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.*;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.ReviewRepository;
import rs.ac.uns.ftn.xwsservice.service.ReviewService;
import rs.ac.uns.ftn.xwsservice.service.UnmarshallerService;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    @Autowired
    private UnmarshallerService unmarshallerService;

    @Autowired
    private DOMParserImpl domParser;

    @Value("${xsd.path.review}")
    private String reviewXmlSchemaPath;

    @Override
    public String addReview(String xmlData, String processId) throws Exception {
        Document document = domParser.isXmlDataValid(xmlData, reviewXmlSchemaPath);

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String reviewId = UUID.randomUUID().toString();
        String id = reviewRepository.save(xmlData, reviewId);

        PoslovniProces proces = businessProcessRepository.findObjectById(processId);
        CTRecenzent chosen = null;
        for (CTRecenzent recenzent : proces.getRecenzenti().getRecenzent()) {
            if (recenzent.getRecenzentID().equals(loggedUser.getId().toString())) {
                chosen = recenzent;
            }
        }

        chosen.getRecenzije().getRecenzijaID().add(id);

        businessProcessRepository.saveObject(proces);
        return id;
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
    public Recenzija getReviewObjectById(String id) throws Exception {
        String xmlData = this.getReviewById(id);
        Recenzija recenzija = (Recenzija) unmarshallerService.unmarshal(xmlData);
        return recenzija;
    }
}
