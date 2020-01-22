package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.PropratnoPismo;
import rs.ac.uns.ftn.xwsservice.repository.CoverLetterRepo;
import rs.ac.uns.ftn.xwsservice.service.CoverLetterService;
import rs.ac.uns.ftn.xwsservice.service.UnmarshallerService;
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
    private UnmarshallerService unmarshallerService;

    @Autowired
    private CoverLetterRepo coverLetterRepo;

    @Override
    public String addCoverLetter(String coverLetterXmlData) throws Exception {
        Document document = domParser.isXmlDataValid(coverLetterXmlData, this.coverLetterSchemaPath);

        String coverLetterId = UUID.randomUUID().toString();
        String updatedXmlData = CoverLetterIdUtil.addCoverLetterId(coverLetterXmlData, coverLetterId);

        String id = coverLetterRepo.save(updatedXmlData, coverLetterId);

        String xsltOutputPath = coverLetterXsltOutputFolder + coverLetterId;
        xsltService.transform(coverLetterXmlData, coverLetterXsltPath, xsltOutputPath);

        String xslfoOutputPath = coverLetterXslfoOutputFolder + coverLetterId;
        xslfoService.transform(coverLetterXmlData, coverLetterXslfoPath, xslfoOutputPath);

        return id;
    }

    @Override
    public String findCoverLetterXmlById(String id) throws Exception {
        String xml = coverLetterRepo.findById(id);

        if (xml == null) {
            throw new ResourceNotFoundException("Cover letter with ID " + id + " doesn't exist.");
        }

        PropratnoPismo coverLetter = (PropratnoPismo) unmarshallerService.unmarshal(xml);

        return xml;
    }

    @Override
    public PropratnoPismo findCoverLetterById(String id) throws Exception {
        String xmlData = this.findCoverLetterXmlById(id);
        PropratnoPismo coverLetter = (PropratnoPismo) unmarshallerService.unmarshal(xmlData);
        return coverLetter;
    }

}
