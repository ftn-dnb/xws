package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Value("${xslfo.path.publication}")
    private String xslfoFile;

    @Value("${xslt.path.publication}")
    private String publicationXsltFilePath;

    @Value("${xslt.path.output-folder.publications}")
    private String publicationXsltOutputFolderPath;

    @Value("${xslfo.path.output-folder.publications}")
    private String xslfooutput;

    @PostMapping("/public")
    public ResponseEntity transformations(@RequestBody String xmlData) throws Exception {
        xslfoService.transform(xmlData, xslfoFile, xslfooutput + "rezultat");
        xsltService.transform(xmlData, publicationXsltFilePath, publicationXsltOutputFolderPath + "rezultat");
        return ResponseEntity.ok().build();
    }
}
