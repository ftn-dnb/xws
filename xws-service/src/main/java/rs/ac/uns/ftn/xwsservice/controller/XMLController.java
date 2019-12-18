package rs.ac.uns.ftn.xwsservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.XMLDto;
import rs.ac.uns.ftn.xwsservice.service.XMLService;

import java.util.ArrayList;

@CrossOrigin(origins = "xmldb:exist://localhost:8899")
@RestController
@RequestMapping(value = "/xml")
public class XMLController {

    @Autowired
    XMLService xmlService;

    @GetMapping(path = "/unmarshall")
    public ResponseEntity<String> retrieveUnmarshall(@RequestBody XMLDto xmlDto) {
        String response = xmlService.retrieveObjectString(xmlDto.getXmlFile());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/xpath")
    public ResponseEntity<ArrayList<String>> retrieveXPath(@RequestBody XMLDto xmlDto) {
        ArrayList<String> response = xmlService.retrieveXPath(xmlDto.getxPathExp());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
