package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.request.ReviewReqDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewDTO;
import rs.ac.uns.ftn.xwsservice.model.Recenzija;
import rs.ac.uns.ftn.xwsservice.service.FileService;
import rs.ac.uns.ftn.xwsservice.service.ReviewService;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Value("${xslt.path.output-folder.reviews}")
    private String reviewsOutputHtmlFolder;

    @Value("${xslfo.path.output-folder.reviews}")
    private String reviewsOutputPdfFolder;

    @Autowired
    private FileService fileService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable String id) throws Exception {
        Recenzija recenzija = reviewService.getReviewObjectById(id);
        return new ResponseEntity<>(new ReviewDTO(recenzija), HttpStatus.OK);
    }

    @GetMapping("/process/{id}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProcessId(@PathVariable String id) throws Exception {
        List<ReviewDTO> recenzije = reviewService.getReviewsByProcessId(id);
        return new ResponseEntity<>(recenzije, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewReqDTO reviewReqDTO) throws Exception {
        String id = reviewService.addReview(reviewReqDTO.getXmlData(), reviewReqDTO.getProcessId());
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/public/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getReviewPdf(@PathVariable String id) {
        String path = reviewsOutputPdfFolder + id;
        ByteArrayInputStream bis = fileService.readPdfFile(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/public/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getReviewHtml(@PathVariable String id) {
        String path = reviewsOutputHtmlFolder + id;
        return new ResponseEntity<>(fileService.readHtmlFile(path), HttpStatus.OK);
    }
}
