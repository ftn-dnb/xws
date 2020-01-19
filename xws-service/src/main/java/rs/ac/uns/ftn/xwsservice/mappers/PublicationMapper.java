package rs.ac.uns.ftn.xwsservice.mappers;

import rs.ac.uns.ftn.xwsservice.dto.response.PublicationDTO;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

import java.util.List;
import java.util.stream.Collectors;

public class PublicationMapper {

    public static PublicationDTO toDto(NaucniRad pub) {
        return new PublicationDTO(pub);
    }

    public static List<PublicationDTO> toDtoList(List<NaucniRad> pubs) {
        return pubs.stream().map(pub -> new PublicationDTO(pub)).collect(Collectors.toList());
    }

    private PublicationMapper() {}

}
