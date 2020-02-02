package rs.ac.uns.ftn.xwsservice.dto.request;

import java.util.Date;

public class SearchMetadataDTO {
    private String keyword;
    private String title;
    private String language;
    private String type;
    private String autor1;
    private String autor2;
    private String startDateCreated;
    private String endDateCreated;
    private String startDatePublished;
    private String endDatePublished;

    public SearchMetadataDTO(){
        super();
    }

    public SearchMetadataDTO(String keyword, String title, String language, String type, String autor1, String autor2, String startDateCreated, String endDateCreated, String startDatePublished, String endDatePublished) {
        this.keyword = keyword;
        this.title = title;
        this.language = language;
        this.type = type;
        this.autor1 = autor1;
        this.autor2 = autor2;
        this.startDateCreated = startDateCreated;
        this.endDateCreated = endDateCreated;
        this.startDatePublished = startDatePublished;
        this.endDatePublished = endDatePublished;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getStartDateCreated() {
        return startDateCreated;
    }

    public void setStartDateCreated(String startDateCreated) {
        this.startDateCreated = startDateCreated;
    }

    public String getEndDateCreated() {
        return endDateCreated;
    }

    public void setEndDateCreated(String endDateCreated) {
        this.endDateCreated = endDateCreated;
    }

    public String getStartDatePublished() {
        return startDatePublished;
    }

    public void setStartDatePublished(String startDatePublished) {
        this.startDatePublished = startDatePublished;
    }

    public String getEndDatePublished() {
        return endDatePublished;
    }

    public void setEndDatePublished(String endDatePublished) {
        this.endDatePublished = endDatePublished;
    }

    public String getAutor1() {
        return autor1;
    }

    public void setAutor1(String autor1) {
        this.autor1 = autor1;
    }

    public String getAutor2() {
        return autor2;
    }

    public void setAutor2(String autor2) {
        this.autor2 = autor2;
    }
}
