package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.xwsservice.service.FileService;

import java.io.ByteArrayInputStream;

/**
 *  Ovaj kontroler je samo primer kako se dobavljaju fajlovi sa diska
 *  i kako se salju u HTML i PDF formatu.
 */
@RestController
@RequestMapping("/api/files/")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(path = "/public/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPublicationPdfFile(@PathVariable String id) {
        ByteArrayInputStream bis = fileService.readPdfFile(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(path = "/public/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getPublicationHtmlFile(@PathVariable String id) {
        return new ResponseEntity<>(fileService.readHtmlFile(id), HttpStatus.OK);
    }
}
