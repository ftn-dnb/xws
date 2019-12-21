package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.xwsservice.service.CoverLetterService;

@RestController
@RequestMapping("/api/coverLetters")
public class CoverLetterController {

    @Autowired
    private CoverLetterService coverLetterService;

    @PostMapping
    public ResponseEntity addCoverLetter(@RequestBody String xmlCoverLetter) {
        coverLetterService.addCoverLetter(xmlCoverLetter);
        return ResponseEntity.ok().build();
    }
}
