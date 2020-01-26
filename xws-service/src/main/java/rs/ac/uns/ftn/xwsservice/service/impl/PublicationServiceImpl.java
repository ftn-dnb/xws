package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.EnumStatusRada;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
import rs.ac.uns.ftn.xwsservice.model.User;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.PublicationRepo;
import rs.ac.uns.ftn.xwsservice.service.*;
import rs.ac.uns.ftn.xwsservice.utils.PublicationIdUtil;

import java.util.ArrayList;
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

    @Autowired
    private BusinessProcessService businessProcessService;

    @Autowired
    private BusinessProcessRepository businessProcessRepository;


    @Override
    public String addPublication(String publicationXmlData) throws Exception {
        Document document = domParser.isXmlDataValid(publicationXmlData, publicationSchemaPath);

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String pubId = UUID.randomUUID().toString();

        String updatedXml = PublicationIdUtil.addAuthorId(PublicationIdUtil.addPublicationId(publicationXmlData, pubId), loggedUser.getId().toString());

        String id = publicationRepo.save(updatedXml, pubId);
        NaucniRad rad = publicationRepo.findObjectById(id);
        //TODO: da li na ovaj nacin setovati i id???
        rad.setObrisan(false);

        publicationRepo.saveObject(rad);

        String processId = businessProcessService.createNewProcess(id);

        String xsltOutputFilePath = publicationXsltOutputFolderPath + pubId;
        xsltService.transform(publicationXmlData, publicationXsltFilePath, xsltOutputFilePath);

        String xslfoOutputFilePath = publicationXslfoOutputFolderPath + pubId;
        xslfoService.transform(publicationXmlData, publicationXslfoFilePath, xslfoOutputFilePath);

        // TODO: Dodati i transofrmacije za anonimne verzije naucnog rada

        return processId;
    }

    @Override
    public List<NaucniRad> getAllPublications() throws Exception {
        ArrayList<PoslovniProces> processes = new ArrayList<>(businessProcessService.getAllProcesses());
        ArrayList<NaucniRad> publications = new ArrayList<>();
        for (PoslovniProces process : processes) {
            if (process.getStatusRada() == EnumStatusRada.PRIHVACEN) {
                NaucniRad publication = findPublicationById(process.getNaucniRadId());
                publications.add(publication);
            }
        }
        return publications;
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
    public String deletePublication(String id) throws Exception {
        NaucniRad publication = this.publicationRepo.findObjectById(id);
        publication.setObrisan(true);
        this.publicationRepo.saveObject(publication);

        PoslovniProces process = businessProcessService.getProcessByPublicationId(id);
        process.setStatusRada(EnumStatusRada.OBRISAN);
        businessProcessRepository.saveObject(process);

        return id;
    }

    @Override
    public String getRdfMetadata(String id) throws Exception {
        String xmlPublication = publicationRepo.findById(id);

        if (xmlPublication == null) {
            throw new ResourceNotFoundException("Publication with ID " + id + " doesn't exist.");
        }

        String result = metadataExtractorService.extractMetadataToRdf(xmlPublication);
        return result;
    }

    @Override
    public String getJsonMetadata(String id) throws Exception {
        String xmlPublication = publicationRepo.findById(id);

        if (xmlPublication == null) {
            throw new ResourceNotFoundException("Publication with ID " + id + " doesn't exist.");
        }

        String result = metadataExtractorService.extractMetadataToJson(xmlPublication);
        return result;
    }
}
