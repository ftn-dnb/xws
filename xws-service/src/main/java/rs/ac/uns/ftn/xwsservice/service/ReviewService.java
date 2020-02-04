package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.dto.response.ReviewDTO;
import rs.ac.uns.ftn.xwsservice.model.Recenzija;

import java.util.List;

public interface ReviewService {

    String addReview(String xmlData, String processId) throws Exception;
    String getReviewById(String id) throws Exception;
    List<ReviewDTO> getReviewsByProcessId(String id) throws Exception;
    Recenzija getReviewObjectById(String id) throws Exception;
}
