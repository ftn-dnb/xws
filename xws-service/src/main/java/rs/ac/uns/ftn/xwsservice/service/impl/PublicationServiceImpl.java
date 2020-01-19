package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.dto.response.PublicationDTO;
import rs.ac.uns.ftn.xwsservice.service.MetadataExtractorService;
import rs.ac.uns.ftn.xwsservice.service.PublicationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Value("${xsd.path.publication}")
    private String publicationSchemaPath;

    @Autowired
    private DOMParserImpl domParser;

    @Autowired
    private MetadataExtractorService metadataExtractorService;

    @Override
    public void addPublication(String publicationXmlData) {
        Document document = domParser.isXmlDataValid(publicationXmlData, publicationSchemaPath);

        // TODO: dodati naucni rad u bazu podataka
        // isXmlDataValid ce baciti izuzetak ako parsiranje nije uspelo, tako da ovde nema potrebe vrsiti
        // proveru da li je parsiranje uspesno
    }

    @Override
    public String getXmlData(String id) {
        // TODO: implementirati ovo kada se napravi repository sloj

        String result = "<note>\n" +
                "<to>Tove</to>\n" +
                "<from>Jani</from>\n" +
                "<heading>Reminder</heading>\n" +
                "<body>Don't forget me this weekend!</body>\n" +
                "</note>";

        return result;
    }

    @Override
    public List<PublicationDTO> getMyPublications() {
        // TODO: Implementirati
        return new ArrayList<>();
    }

    @Override
    public String getRdfMetadata(String id) {
        // TODO: Implementirati
        String result = metadataExtractorService.extractMetadataToRdf("this is some xml data, change later");
        return result;
    }

    @Override
    public String getJsonMetadata(String id) {
        // TODO: Implementirati
        String result = metadataExtractorService.extractMetadataToJson("this is some xml data, change later");
        return result;
    }
}
