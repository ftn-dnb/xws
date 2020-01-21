package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

import java.util.ArrayList;
import java.util.List;

public class PublicationDTO {

    private String id;
    private String title;
    private String textAbstract;
    private List<String> keywords;
    private String xmlFilePath;
    private String htmlFilePath;
    private String pdfFilePath;
    private String coverLetterFilePath;
    private String metadataRdfPath;
    private String metadataJsonPath;
    private List<String> authors;
    private List<ReferenceDTO> references;

    public PublicationDTO() {
    }

    public PublicationDTO(NaucniRad pub) {
        this.id = pub.getId();
        this.title = pub.getNaslovnaStrana().getNaslov();
        this.textAbstract = pub.getAbstrakt();
        this.keywords = pub.getKljucneReci().getKljucnaRec();
        this.xmlFilePath = "http://localhost:8080/api/publications/public/xml/" + id;
        this.htmlFilePath = "http://localhost:8080/api/publications/public/html/" + id;
        this.pdfFilePath = "http://localhost:8080/api/publications/public/pdf/" + id;
        this.metadataJsonPath = "http://localhost:8080/api/publications/public/metadata/json/" + id;
        this.metadataRdfPath = "http://localhost:8080/api/publications/public/metadata/rdf/" + id;

        this.authors = new ArrayList<>();
        pub.getNaslovnaStrana().getAutori().getAutor().forEach(author -> this.authors.add(author.getIme() + " " + author.getPrezime()));

        this.references = new ArrayList<>();
        pub.getReference().getReferenca().forEach(reference -> this.references.add(new ReferenceDTO(reference)));

        // TODO: dodati putanju ka cover letteru, ali treba prvo naci koji je letter za ovaj naucni rad
        // TODO: dodati da flag da se zna da li je rad obrisan ili ne
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextAbstract() {
        return textAbstract;
    }

    public void setTextAbstract(String textAbstract) {
        this.textAbstract = textAbstract;
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

    public String getMetadataRdfPath() {
        return metadataRdfPath;
    }

    public void setMetadataRdfPath(String metadataRdfPath) {
        this.metadataRdfPath = metadataRdfPath;
    }

    public String getMetadataJsonPath() {
        return metadataJsonPath;
    }

    public void setMetadataJsonPath(String metadataJsonPath) {
        this.metadataJsonPath = metadataJsonPath;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<ReferenceDTO> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceDTO> references) {
        this.references = references;
    }
}
