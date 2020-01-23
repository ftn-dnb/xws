package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;

import java.util.List;

public interface BusinessProcessService {

    List<PoslovniProces> getAllProcesses() throws Exception;
    String createNewProcess(String publicationId) throws Exception;
    void addCoverLetterForPublication(String processId, String coverLetterId) throws Exception;
}
