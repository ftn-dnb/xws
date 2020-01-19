package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.dto.response.PublicationDTO;

import java.util.List;

public interface PublicationService {

    void addPublication(String publicationXmlData);
    String getXmlData(String id);
    List<PublicationDTO> getMyPublications();
    String getRdfMetadata(String id);
    String getJsonMetadata(String id);
}
