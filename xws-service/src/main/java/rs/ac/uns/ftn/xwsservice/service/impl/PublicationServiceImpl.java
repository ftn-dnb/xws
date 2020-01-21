package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
import rs.ac.uns.ftn.xwsservice.model.User;
import rs.ac.uns.ftn.xwsservice.repository.PublicationRepo;
import rs.ac.uns.ftn.xwsservice.service.*;
import rs.ac.uns.ftn.xwsservice.utils.PublicationIdUtil;

import java.util.List;
import java.util.UUID;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Value("${xsd.path.publication}")
    private String publicationSchemaPath;

    @Value("${xslt.path.publication}")
    private String publicationXsltFilePath;

    @Value("${xslfo.path.publication}")
    private String publicationXslfoFilePath;

    @Value("${xslt.path.output-folder.publications}")
    private String publicationXsltOutputFolderPath;

    @Value("${xslfo.path.output-folder.publications}")
    private String publicationXslfoOutputFolderPath;

    @Autowired
    private PublicationRepo publicationRepo;

    @Autowired
    private DOMParserImpl domParser;

    @Autowired
    private MetadataExtractorService metadataExtractorService;

    @Autowired
    private UnmarshallerService unmarshallerService;

    @Autowired
    private XSLTService xsltService;

    @Autowired
    private XSLFOService xslfoService;


    @Override
    public void addPublication(String publicationXmlData) throws Exception {
        Document document = domParser.isXmlDataValid(publicationXmlData, publicationSchemaPath);

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String pubId = UUID.randomUUID().toString();

        String updatedXml = PublicationIdUtil.addAuthorId(PublicationIdUtil.addPublicationId(publicationXmlData, pubId), loggedUser.getId().toString());

        String id = publicationRepo.save(updatedXml, pubId);

        String xsltOutputFilePath = publicationXsltOutputFolderPath + pubId;
        xsltService.transform(publicationXmlData, publicationXsltFilePath, xsltOutputFilePath);

        String xslfoOutputFilePath = publicationXslfoOutputFolderPath + pubId;
        xslfoService.transform(publicationXmlData, publicationXslfoFilePath, xslfoOutputFilePath);
    }

    @Override
    public String findPublicationXmlById(String id) throws Exception {
        String xml = publicationRepo.findById(id);

        if (xml == null) {
            throw new ResourceNotFoundException("Publication with ID " + id + " doesn't exist.");
        }

        return xml;
    }

    @Override
    public NaucniRad findPublicationById(String id) throws Exception {
        String xmlData = this.findPublicationXmlById(id);
        NaucniRad publication = (NaucniRad) unmarshallerService.unmarshal(xmlData);
        return publication;
    }

    @Override
    public List<NaucniRad> getPublicationsByUser() throws Exception {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<NaucniRad> myPubs = this.publicationRepo.findByUser(loggedUser.getId().toString());
        return myPubs;
    }

    @Override
    public List<NaucniRad> filterPublicationsByText(String text) throws Exception {
        List<NaucniRad> pubs = this.publicationRepo.filterPublications(text);
        return pubs;
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
