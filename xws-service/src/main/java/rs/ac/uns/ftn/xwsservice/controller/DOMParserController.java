package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.xwsservice.service.impl.DOMParserImpl;

@RestController
@RequestMapping(path = "/api/dom/public/")
public class DOMParserController {

    @Autowired
    private DOMParserImpl domParser;

    @PostMapping("/cover-letter")
    public ResponseEntity validateCoverLetter(@RequestBody String coverLetterXmlData) {
        domParser.parseCoverLetter(coverLetterXmlData);
        return ResponseEntity.ok().build();
    }
}
