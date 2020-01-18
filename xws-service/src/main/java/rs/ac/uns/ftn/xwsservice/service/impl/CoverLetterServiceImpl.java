package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.repository.CoverLetterRepo;
import rs.ac.uns.ftn.xwsservice.service.CoverLetterService;
import rs.ac.uns.ftn.xwsservice.service.XSLFOService;
import rs.ac.uns.ftn.xwsservice.service.XSLTService;
import rs.ac.uns.ftn.xwsservice.utils.CoverLetterIdUtil;

import java.util.UUID;

@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    @Value("${xsd.path.coverletter}")
    private String coverLetterSchemaPath;

    @Value("${xslt.path.cover-letter}")
    private String coverLetterXsltPath;

    @Value("${xslfo.path.cover-letter}")
    private String coverLetterXslfoPath;

    @Value("${xslt.path.output-folder.cover-letters}")
    private String coverLetterXsltOutputFolder;

    @Value("${xslfo.path.output-folder.cover-letters}")
    private String coverLetterXslfoOutputFolder;

    @Autowired
    private DOMParserImpl domParser;

    @Autowired
    private XSLTService xsltService;

    @Autowired
    private XSLFOService xslfoService;

    @Autowired
    private CoverLetterRepo coverLetterRepo;

    @Override
    public void addCoverLetter(String coverLetterXmlData) throws Exception {
        Document document = domParser.isXmlDataValid(coverLetterXmlData, this.coverLetterSchemaPath);

        // TODO: isXmlDataValid ce baciti izuzetak ako parsiranje ne uspe, tako da ovde nema potrebe
        // za proverom da li je parsiranje uspelo

        // TODO: Sacuvati cover letter u bazu
        String coverLetterId = UUID.randomUUID().toString();
        String updatedXmlData = CoverLetterIdUtil.addCoverLetterId(coverLetterXmlData, coverLetterId);

        String id = coverLetterRepo.save(updatedXmlData, coverLetterId);

        String xsltOutputPath = coverLetterXsltOutputFolder + coverLetterId;
        xsltService.transform(coverLetterXmlData, coverLetterXsltPath, xsltOutputPath);

        String xslfoOutputPath = coverLetterXslfoOutputFolder + coverLetterId;
        xslfoService.transform(coverLetterXmlData, coverLetterXslfoPath, xslfoOutputPath);
    }

    @Override
    public String findCoverLetterById(String id) throws Exception {
        String xml = coverLetterRepo.findById(id);
        return xml;
    }

}
