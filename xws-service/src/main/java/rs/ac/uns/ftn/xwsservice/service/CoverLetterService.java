package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.PropratnoPismo;

public interface CoverLetterService {

    void addCoverLetter(String coverLetterXmlData) throws Exception;

    String findCoverLetterXmlById(String id) throws Exception;

    PropratnoPismo findCoverLetterById(String id) throws Exception;
}
