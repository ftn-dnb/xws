package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;

public class ReviewRequestDTO {

    private String processId;
    private String title;
    private String publicationStatus;
    private String publicationPhase;
    private String publicationHtmlFilePath;
    private String publicationPdfFilePath;
    private String reviewStatus;

    public ReviewRequestDTO() {
    }

    public ReviewRequestDTO(PoslovniProces process, NaucniRad publication, String reviewStatus) {
        this.processId = process.getId();
        this.title = publication.getNaslovnaStrana().getNaslov();
        this.publicationStatus = process.getStatusRada().toString();
        this.publicationPhase = process.getFaza().toString();
        this.reviewStatus = reviewStatus;

        // TODO: Promeniti putanje da bi se dobile anonimne verzije radova !!!
        this.publicationPdfFilePath = "http://localhost:8080/api/publications/public/pdf/" + publication.getId();
        this.publicationHtmlFilePath = "http://localhost:8080/api/publications/public/html/" + publication.getId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public String getPublicationPhase() {
        return publicationPhase;
    }

    public void setPublicationPhase(String publicationPhase) {
        this.publicationPhase = publicationPhase;
    }

    public String getPublicationHtmlFilePath() {
        return publicationHtmlFilePath;
    }

    public void setPublicationHtmlFilePath(String publicationHtmlFilePath) {
        this.publicationHtmlFilePath = publicationHtmlFilePath;
    }

    public String getPublicationPdfFilePath() {
        return publicationPdfFilePath;
    }

    public void setPublicationPdfFilePath(String publicationPdfFilePath) {
        this.publicationPdfFilePath = publicationPdfFilePath;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
