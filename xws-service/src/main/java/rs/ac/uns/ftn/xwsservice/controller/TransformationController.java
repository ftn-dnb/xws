package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.xwsservice.service.XSLFOService;
import rs.ac.uns.ftn.xwsservice.service.XSLTService;

/**
 *  TODO: !!!
 *  Ovo je samo primer kako bi se izvrsavale transformacije nad XML podacima
 *  Obrisati kasnije ovu klasu. Trenutno sluzi samo za testiranje transformacija.
 */
@RestController
@RequestMapping("/api/transformations")
public class TransformationController {

    @Autowired
    private XSLFOService xslfoService;

    @Autowired
    private XSLTService xsltService;

    @Value("${xslfo.path.bookstore}")
    private String xslfoFile;

    @Value("${xslt.path.bookstore}")
    private String xsltFile;

    @PostMapping("/public")
    public ResponseEntity transformations(@RequestBody String xmlData) throws Exception {
        xslfoService.transform(xmlData, xslfoFile, "mojaknjiga");
        xsltService.transform(xmlData, xsltFile, "mojaknjiga");
        return ResponseEntity.ok().build();
    }
}
