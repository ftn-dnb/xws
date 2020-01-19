package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.response.PublicationDTO;
import rs.ac.uns.ftn.xwsservice.service.FileService;
import rs.ac.uns.ftn.xwsservice.service.PublicationService;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private FileService fileService;

    @Value("${xslt.path.output-folder.publications}")
    private String publicationHtmlFolderPath;

    @Value("${xslfo.path.output-folder.publications}")
    private String publicationPdfFolderPath;

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

    @GetMapping(path = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPublicationPdfFile(@PathVariable String id) {
        String path = publicationPdfFolderPath + id;
        ByteArrayInputStream bis = fileService.readPdfFile(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=publication-" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getPublicationHtmlFile(@PathVariable String id) {
        String path = publicationHtmlFolderPath + id;
        return new ResponseEntity<>(fileService.readHtmlFile(path), HttpStatus.OK);
    }

    @GetMapping(path = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPublicationXmlFile(@PathVariable String id) {
        return new ResponseEntity<>(publicationService.getXmlData(id), HttpStatus.OK);
    }

    @GetMapping(path = "/metadata/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPublicationMetadataRdfFormat(@PathVariable String id) {
        return new ResponseEntity<>(publicationService.getRdfMetadata(id), HttpStatus.OK);
    }

    @GetMapping(path = "/metadata/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPublicationMetadataJsonFormat(@PathVariable String id) {
        return new ResponseEntity<>(publicationService.getJsonMetadata(id), HttpStatus.OK);
    }
}
