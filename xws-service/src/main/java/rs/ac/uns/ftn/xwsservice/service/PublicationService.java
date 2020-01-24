package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.NaucniRad;

import java.util.List;

public interface PublicationService {

    String addPublication(String publicationXmlData) throws Exception;
    NaucniRad findPublicationById(String id) throws Exception;
    String findPublicationXmlById(String id) throws Exception;
    List<NaucniRad> getPublicationsByUser() throws Exception;
    List<NaucniRad> getAllPublications() throws Exception;
    String deletePublication(String id) throws Exception;
    List<NaucniRad> filterPublicationsByText(String text) throws Exception;
    String getRdfMetadata(String id) throws Exception;
    String getJsonMetadata(String id) throws Exception;
}
