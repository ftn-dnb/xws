package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import rs.ac.uns.ftn.xwsservice.exception.ApiRequestException;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.EnumStatusRada;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
import rs.ac.uns.ftn.xwsservice.model.User;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.PublicationRepo;
import rs.ac.uns.ftn.xwsservice.service.*;
import rs.ac.uns.ftn.xwsservice.utils.PublicationIdUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Value("${xsd.path.publication}")
    private String publicationSchemaPath;

    @Value("${xslt.path.publication}")
    private String publicationXsltFilePath;

    @Value("${xslt.path.publication.anonymous}")
    private String publicationAnonymousXsltFilePath;

    @Value("${xslfo.path.publication}")
    private String publicationXslfoFilePath;

    @Value("${xslfo.path.publication.anonymous}")
    private String publicationAnonymousXslfoFilePath;

    @Value("${xslt.path.output-folder.publications}")
    private String publicationXsltOutputFolderPath;

    @Value("${xslt.path.output-folder.publications.anonymous}")
    private String publicationAnonymousXsltOutputFolderPath;

    @Value("${xslt.path.output-folder.publications.rdf}")
    private String publicationRDFXsltOutputFolderPath;

    @Value("${xslfo.path.output-folder.publications}")
    private String publicationXslfoOutputFolderPath;

    @Value("${xslfo.path.output-folder.publications.anonymous}")
    private String publicationAnonymousXslfoOutputFolderPath;

    @Value("${xslt.path.publication.rdf}")
    private String publicationXsltRDFFilePath;
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

    @Autowired
    private MetadataService metadataService;

    @Override
    public String addPublication(String publicationXmlData) throws Exception {
        Document document = domParser.isXmlDataValid(publicationXmlData, publicationSchemaPath);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String pubId = UUID.randomUUID().toString();

        String updatedXml = PublicationIdUtil.addAuthorId(PublicationIdUtil.addPublicationId(publicationXmlData, pubId), loggedUser.getId().toString());

        updatedXml = PublicationIdUtil.addDates(updatedXml);

        String id = publicationRepo.save(updatedXml, pubId);
        NaucniRad rad = publicationRepo.findObjectById(id);
        //TODO: da li na ovaj nacin setovati i id???
        rad.setObrisan(false);
        publicationRepo.saveObject(rad);

        String processId = businessProcessService.createNewProcess(id);

        // Normal transformations
        String xsltOutputFilePath = publicationXsltOutputFolderPath + pubId;
        xsltService.transform(publicationXmlData, publicationXsltFilePath, xsltOutputFilePath);

        String xslfoOutputFilePath = publicationXslfoOutputFolderPath + pubId;
        xslfoService.transform(publicationXmlData, publicationXslfoFilePath, xslfoOutputFilePath);

        // Anonymous transformations
        String xsltAnonymousOutputFilePath = publicationAnonymousXsltOutputFolderPath + pubId;
        xsltService.transform(publicationXmlData, publicationAnonymousXsltFilePath, xsltAnonymousOutputFilePath);

        String xslfoAnonymousOutputFilePath = publicationAnonymousXslfoOutputFolderPath + pubId;
        xslfoService.transform(publicationXmlData, publicationAnonymousXslfoFilePath, xslfoAnonymousOutputFilePath);

        //MetaDataExtraction
        //rdf
        String rdfTransformationOutputFilePath = publicationRDFXsltOutputFolderPath + pubId + ".rdf";
        xsltService.transformRDF(publicationRepo.findById(pubId), publicationXsltRDFFilePath, rdfTransformationOutputFilePath);
        //grddl
        String resultMeta = metadataExtractorService.extractMetadataToRdf(new FileInputStream(new File(rdfTransformationOutputFilePath)), pubId);
        //upload
        metadataService.metadataWrite(resultMeta);
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

        if (publication == null) {
            throw new ResourceNotFoundException("Publication with ID " + id + " doesn't exist");
        }

        PoslovniProces process = businessProcessService.getProcessByPublicationId(id);

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!publication.getNaslovnaStrana().getAutori().getAutor().get(0).getId().equals(loggedUser.getId().toString())) {
            throw new ApiRequestException("You can't delete this publication");
        }

        if (process == null) {
            throw new ResourceNotFoundException("Business process for publication with ID " + id + "doesn't exist");
        }

        if (!process.getStatusRada().equals(EnumStatusRada.U_PROCESU)) {
            throw new ApiRequestException("You cant cancel this publication.");
        }

        publication.setObrisan(true);
        this.publicationRepo.saveObject(publication);

        process.setStatusRada(EnumStatusRada.OBRISAN);
        businessProcessRepository.saveObject(process);

        return id;
    }

    // PROVERITI SVRHU OVE F-je
    @Override
    public String getRdfMetadata(String id) throws Exception {
        String xmlPublication = publicationRepo.findById(id);

        if (xmlPublication == null) {
            throw new ResourceNotFoundException("Publication with ID " + id + " doesn't exist.");
        }

        String result = metadataExtractorService.extractMetadataToRdf(xmlPublication);
        //String result = "Ne radi";
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

    @Override
    public List<String> filterPublicationByStatus(List<String> result) throws Exception {
        List<String> finalResult = new ArrayList<>();

        for (String id:
             result) {
            PoslovniProces p = businessProcessRepository.findByPublicationId(id);
            if (p.getStatusRada() == EnumStatusRada.PRIHVACEN){
                finalResult.add(id);
            }
        }
        return finalResult;
    }
}
