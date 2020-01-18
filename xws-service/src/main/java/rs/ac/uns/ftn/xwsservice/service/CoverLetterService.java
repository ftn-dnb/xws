package rs.ac.uns.ftn.xwsservice.service;

public interface CoverLetterService {

    void addCoverLetter(String coverLetterXmlData) throws Exception;

    String findCoverLetterById(String id) throws Exception;
}
