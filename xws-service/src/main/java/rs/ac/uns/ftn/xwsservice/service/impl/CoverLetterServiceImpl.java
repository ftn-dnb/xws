package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.service.CoverLetterService;

@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    @Value("${xsd.path.coverletter}")
    private String coverLetterSchemaPath;

    @Autowired
    private DOMParserImpl domParser;

    @Override
    public void addCoverLetter(String coverLetterXmlData) {
        Document document = domParser.isXmlDataValid(coverLetterXmlData, this.coverLetterSchemaPath);

        // TODO: isXmlDataValid ce baciti izuzetak ako parsiranje ne uspe, tako da ovde nema potrebe
        // za proverom da li je parsiranje uspelo
    }

}
