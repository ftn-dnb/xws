package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.request.ReviewReqDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewDTO;
import rs.ac.uns.ftn.xwsservice.model.Recenzija;
import rs.ac.uns.ftn.xwsservice.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable String id) throws Exception {
        Recenzija recenzija = reviewService.getReviewObjectById(id);
        return new ResponseEntity<>(new ReviewDTO(recenzija), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewReqDTO reviewReqDTO) throws Exception {
        String id = reviewService.addReview(reviewReqDTO.getXmlData(), reviewReqDTO.getProcessId());
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
