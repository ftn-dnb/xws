package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.CTReferenca;

import java.util.List;

public class ReferenceDTO {

    private String url;
    private String title;
    private List<String> authors;

    public ReferenceDTO() {
    }

    public ReferenceDTO(CTReferenca reference) {
        this.url = reference.getURLRada();
        this.title = reference.getNazivRada();
        this.authors = reference.getAutor();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
