package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

import java.util.List;

public class PublicationDTO {

    private String id;
    private String publicationTitle;
    private List<String> keywords;

    public PublicationDTO() {
    }

    public PublicationDTO(NaucniRad pub) {
        this.id = pub.getID();
        this.publicationTitle = pub.getNaslovnaStrana().getNaslov();
        this.keywords = pub.getKljucneReci().getKljucnaRec();

        // TODO: dodati da flag da se zna da li je rad obrisan ili ne
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
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
}
