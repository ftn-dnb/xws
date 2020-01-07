package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.service.CoverLetterService;
import rs.ac.uns.ftn.xwsservice.service.FileService;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/coverLetters")
public class CoverLetterController {

    @Autowired
    private CoverLetterService coverLetterService;

    @Autowired
    private FileService fileService;

    @Value("${xslt.path.output-folder.cover-letters}")
    private String coverLetterHtmlFolderPath;

    @Value("${xslfo.path.output-folder.cover-letters}")
    private String coverLetterPdfFolderPath;

    @PostMapping
    public ResponseEntity addCoverLetter(@RequestBody String xmlCoverLetter) throws Exception {
        coverLetterService.addCoverLetter(xmlCoverLetter);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getCoverLetterPdfFile(@PathVariable String id) {
        String path = coverLetterPdfFolderPath + id;
        ByteArrayInputStream bis = fileService.readPdfFile(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cover-letter-" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getCoverLetterHtmlFile(@PathVariable String id) {
        String path = coverLetterHtmlFolderPath + id;
        return new ResponseEntity<>(fileService.readHtmlFile(path), HttpStatus.OK);
    }
}
