package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

import java.util.List;

public class PublicationDTO {

    private String id;
    private String title;
    private List<String> keywords;
    private String xmlFilePath;
    private String htmlFilePath;
    private String pdfFilePath;
    private String coverLetterFilePath;

    public PublicationDTO() {
    }

    public PublicationDTO(NaucniRad pub) {
        this.id = pub.getID();
        this.title = pub.getNaslovnaStrana().getNaslov();
        this.keywords = pub.getKljucneReci().getKljucnaRec();
        this.xmlFilePath = "http://localhost:8080/api/publications/public/xml/" + id;
        this.htmlFilePath = "http://localhost:8080/api/publications/public/html/" + id;
        this.pdfFilePath = "http://localhost:8080/api/publications/public/pdf/" + id;

        // TODO: dodati putanju ka cover letteru, ali treba prvo naci koji je letter za ovaj naucni rad
        // TODO: dodati da flag da se zna da li je rad obrisan ili ne
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public void setHtmlFilePath(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

    public String getCoverLetterFilePath() {
        return coverLetterFilePath;
    }

    public void setCoverLetterFilePath(String coverLetterFilePath) {
        this.coverLetterFilePath = coverLetterFilePath;
    }
}
