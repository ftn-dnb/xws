package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.request.FilterPubsDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.PublicationDTO;
import rs.ac.uns.ftn.xwsservice.mappers.PublicationMapper;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
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

    @Value("${xslt.path.output-folder.publications.anonymous}")
    private String publicationAnonymousHtmlFolderPath;

    @Value("${xslfo.path.output-folder.publications}")
    private String publicationPdfFolderPath;

    @Value("${xslfo.path.output-folder.publications.anonymous}")
    private String publicationAnonymousPdfFolderPath;

    @GetMapping(value = "/public/all")
    public ResponseEntity<List<PublicationDTO>> getAllPublications() throws Exception {
        List<NaucniRad> pubs = publicationService.getAllPublications();
        return new ResponseEntity<>(PublicationMapper.toDtoList(pubs), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addPublication(@RequestBody String xmlPublicationData) throws Exception {
        String processId = publicationService.addPublication(xmlPublicationData);
        return new ResponseEntity<>(processId, HttpStatus.OK);
    }

    @GetMapping(path = "/by-user")
    public ResponseEntity<List<PublicationDTO>> getPublicationsByLoggedUser() throws Exception {
        List<NaucniRad> pubs = publicationService.getPublicationsByUser();
        return new ResponseEntity<>(PublicationMapper.toDtoList(pubs), HttpStatus.OK);
    }

    @PostMapping(path = "/public/filter")
    public ResponseEntity<List<PublicationDTO>> filterPublications(@RequestBody FilterPubsDTO filter) throws Exception {
        List<NaucniRad> pubs = this.publicationService.filterPublicationsByText(filter.getFilterText());
        return new ResponseEntity<>(PublicationMapper.toDtoList(pubs), HttpStatus.OK);
    }

    @GetMapping(path = "/public/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPublicationPdfFile(@PathVariable String id) {
        return this.getPdfFile(publicationPdfFolderPath, id);
    }

    @GetMapping(path = "/public/anonymous/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPublicationAnonymousPdfFile(@PathVariable String id) {
        return this.getPdfFile(publicationAnonymousPdfFolderPath, id);
    }

    private ResponseEntity<InputStreamResource> getPdfFile(String folderPath, String id) {
        ByteArrayInputStream bis = fileService.readPdfFile(folderPath + id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=publication-" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/public/{id}")
    public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable String id) throws Exception {
        NaucniRad publication = publicationService.findPublicationById(id);
        return new ResponseEntity<>(PublicationMapper.toDto(publication), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable String id) throws Exception {
        String deletedId = publicationService.deletePublication(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @GetMapping(path = "/public/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPublicationByIdXml(@PathVariable String id) throws Exception {
        String xml = publicationService.findPublicationXmlById(id);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @GetMapping(path = "/public/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getPublicationHtmlFile(@PathVariable String id) {
        String path = publicationHtmlFolderPath + id;
        return new ResponseEntity<>(fileService.readHtmlFile(path), HttpStatus.OK);
    }

    @GetMapping(path = "/public/anonymous/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getPublicationAnonymousHtmlFile(@PathVariable String id) {
        String path = publicationAnonymousHtmlFolderPath + id;
        return new ResponseEntity<>(fileService.readHtmlFile(path), HttpStatus.OK);
    }

    @GetMapping(path = "/public/metadata/rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPublicationMetadataRdfFormat(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(publicationService.getRdfMetadata(id), HttpStatus.OK);
    }

    @GetMapping(path = "/public/metadata/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPublicationMetadataJsonFormat(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(publicationService.getJsonMetadata(id), HttpStatus.OK);
    }
}
