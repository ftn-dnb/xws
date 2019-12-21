package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.service.PublicationService;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Value("${xsd.path.publication}")
    private String publicationSchemaPath;

    @Autowired
    private DOMParserImpl domParser;

    @Override
    public void addPublication(String publicationXmlData) {
        Document document = domParser.isXmlDataValid(publicationXmlData, publicationSchemaPath);

        // TODO: dodati naucni rad u bazu podataka
        // isXmlDataValid ce baciti izuzetak ako parsiranje nije uspelo, tako da ovde nema potrebe vrsiti
        // proveru da li je parsiranje uspesno
    }
}
