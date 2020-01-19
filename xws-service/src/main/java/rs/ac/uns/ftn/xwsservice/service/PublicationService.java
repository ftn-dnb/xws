package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.dto.response.PublicationDTO;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

import java.util.List;

public interface PublicationService {

    void addPublication(String publicationXmlData) throws Exception;
    NaucniRad findPublicationById(String id) throws Exception;
    String findPublicationXmlById(String id) throws Exception;
    List<NaucniRad> getPublicationsByUser() throws Exception;
    String getRdfMetadata(String id);
    String getJsonMetadata(String id);
}
