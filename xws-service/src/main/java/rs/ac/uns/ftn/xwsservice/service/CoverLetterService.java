package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.PropratnoPismo;

public interface CoverLetterService {

    String addCoverLetter(String coverLetterXmlData) throws Exception;
    String addCoverLetterForPublication(String processId, String coverLetterXmlData) throws Exception;
    String submitCoverLetterForPublication(String processId, String coverLetterXmlData) throws Exception;
    String findCoverLetterXmlById(String id) throws Exception;
    PropratnoPismo findCoverLetterById(String id) throws Exception;
}
