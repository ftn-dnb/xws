package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.PropratnoPismo;

public class CoverLetterDTO {

    private String id;

    public CoverLetterDTO() {
    }

    public CoverLetterDTO(PropratnoPismo coverLetter) {
        this.id = coverLetter.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
