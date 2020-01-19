package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

public class PublicationDTO {

    // TODO: dodati ostale atribute

    private String id;
    private String publicationTitle;

    public PublicationDTO() {
    }

    public PublicationDTO(NaucniRad pub) {
        this.id = pub.getID();
        this.publicationTitle = pub.getAbstrakt();
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
}
