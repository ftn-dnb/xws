package rs.ac.uns.ftn.xwsservice.mappers;

import rs.ac.uns.ftn.xwsservice.dto.response.CoverLetterDTO;
import rs.ac.uns.ftn.xwsservice.model.PropratnoPismo;

public class CoverLetterMapper {

    public static CoverLetterDTO toDto(PropratnoPismo coverLetter) {
        return new CoverLetterDTO(coverLetter);
    }

    private CoverLetterMapper() {
    }
}
