package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;

public class BusinessProcessDTO {

    private String id;
    private String processPhase;

    private String publicationId;
    private String publicationStatus;
    private String publicationXmlFilePath;
    private String publicationPdfFilePath;
    private String publicationHtmlFilePath;
    private String publicationMetadataRdfFilePath;
    private String publicationMetadataJsonFilePath;
    private String publicationTitle;

    private String coverLetterId;
    private String coverLetterPdfFilePath;
    private String coverLetterHtmlFilePath;

    public BusinessProcessDTO() {
    }

    public BusinessProcessDTO(PoslovniProces process) {
        this.id = process.getId();
        this.processPhase = process.getFaza().value();
        this.publicationStatus = process.getStatusRada().value();
        this.publicationId = process.getNaucniRadId();
        this.coverLetterId = process.getPropratnoPismoId();
        this.publicationXmlFilePath = "http://localhost:8080/api/publications/public/xml/" + publicationId;
        this.publicationHtmlFilePath = "http://localhost:8080/api/publications/public/html/" + publicationId;
        this.publicationPdfFilePath = "http://localhost:8080/api/publications/public/pdf/" + publicationId;
        this.publicationMetadataJsonFilePath = "http://localhost:8080/api/publications/public/metadata/json/" + publicationId;
        this.publicationMetadataRdfFilePath = "http://localhost:8080/api/publications/public/metadata/rdf/" + publicationId;
        this.coverLetterHtmlFilePath = "http://localhost:8080/api/coverLetters/public/html/" + coverLetterId;
        this.coverLetterPdfFilePath = "http://localhost:8080/api/coverLetters/public/pdf/" + coverLetterId;
    }

    public BusinessProcessDTO(PoslovniProces process, NaucniRad publication) {
        this(process);
        this.publicationTitle = publication.getNaslovnaStrana().getNaslov().getValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessPhase() {
        return processPhase;
    }

    public void setProcessPhase(String processPhase) {
        this.processPhase = processPhase;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public String getPublicationXmlFilePath() {
        return publicationXmlFilePath;
    }

    public void setPublicationXmlFilePath(String publicationXmlFilePath) {
        this.publicationXmlFilePath = publicationXmlFilePath;
    }

    public String getPublicationPdfFilePath() {
        return publicationPdfFilePath;
    }

    public void setPublicationPdfFilePath(String publicationPdfFilePath) {
        this.publicationPdfFilePath = publicationPdfFilePath;
    }

    public String getPublicationHtmlFilePath() {
        return publicationHtmlFilePath;
    }

    public void setPublicationHtmlFilePath(String publicationHtmlFilePath) {
        this.publicationHtmlFilePath = publicationHtmlFilePath;
    }

    public String getPublicationMetadataRdfFilePath() {
        return publicationMetadataRdfFilePath;
    }

    public void setPublicationMetadataRdfFilePath(String publicationMetadataRdfFilePath) {
        this.publicationMetadataRdfFilePath = publicationMetadataRdfFilePath;
    }

    public String getPublicationMetadataJsonFilePath() {
        return publicationMetadataJsonFilePath;
    }

    public void setPublicationMetadataJsonFilePath(String publicationMetadataJsonFilePath) {
        this.publicationMetadataJsonFilePath = publicationMetadataJsonFilePath;
    }

    public String getCoverLetterId() {
        return coverLetterId;
    }

    public void setCoverLetterId(String coverLetterId) {
        this.coverLetterId = coverLetterId;
    }

    public String getCoverLetterPdfFilePath() {
        return coverLetterPdfFilePath;
    }

    public void setCoverLetterPdfFilePath(String coverLetterPdfFilePath) {
        this.coverLetterPdfFilePath = coverLetterPdfFilePath;
    }

    public String getCoverLetterHtmlFilePath() {
        return coverLetterHtmlFilePath;
    }

    public void setCoverLetterHtmlFilePath(String coverLetterHtmlFilePath) {
        this.coverLetterHtmlFilePath = coverLetterHtmlFilePath;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }
}
