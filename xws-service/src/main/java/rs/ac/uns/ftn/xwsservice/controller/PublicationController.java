package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.PublicationDTO;
import rs.ac.uns.ftn.xwsservice.service.PublicationService;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    /**
     *  Dodavanje novog naucnog rada
     *  TODO: dodati PreAuthorize
     */
    @PostMapping
    public ResponseEntity addPublication(@RequestBody String xmlPublicationData) {
        publicationService.addPublication(xmlPublicationData);
        return ResponseEntity.ok().build();
    }

    /**
     *  Autor moze da dobije listu svojih naucnih radova, bez obzira u kom su
     *  delu procesa objavljivanja.
     *  TODO: Dodati PreAuthorize
     */
    @GetMapping("/myPublications")
    public ResponseEntity<List<PublicationDTO>> getMyPublications() {
        return new ResponseEntity<>(publicationService.getMyPublications(), HttpStatus.OK);
    }

}
