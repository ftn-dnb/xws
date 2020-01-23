package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;

import java.util.List;

public interface BusinessProcessService {

    List<PoslovniProces> getAllProcesses() throws Exception;
    PoslovniProces getProcess(String id) throws Exception;
    String createNewProcess(String publicationId) throws Exception;
    void addCoverLetterForPublication(String processId, String coverLetterId) throws Exception;
    void addReviewersToProcess(String processId, List<String> users) throws Exception;
    void changeProcessPhase(String processId, String phase) throws Exception;
    void changeProcessStatus(String processId, boolean status) throws Exception;
}
