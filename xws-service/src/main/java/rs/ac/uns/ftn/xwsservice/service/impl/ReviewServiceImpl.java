package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.UserDTO;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.*;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.ReviewRepository;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.ReviewService;
import rs.ac.uns.ftn.xwsservice.service.UnmarshallerService;
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

    @Value("${xsd.path.review}")
    private String reviewXmlSchemaPath;

    @Override
    public String addReview(String xmlData, String processId) throws Exception {
        Document document = domParser.isXmlDataValid(xmlData, reviewXmlSchemaPath);

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String reviewId = UUID.randomUUID().toString();
        String updatedXmlData = ReviewIdUtil.addReviewId(xmlData, reviewId);

        String id = reviewRepository.save(updatedXmlData, reviewId);

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
