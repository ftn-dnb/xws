package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.xwsservice.service.PublicationService;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping
    public ResponseEntity addPublication(@RequestBody String xmlPublicationData) {
        publicationService.addPublication(xmlPublicationData);
        return ResponseEntity.ok().build();
    }
}
