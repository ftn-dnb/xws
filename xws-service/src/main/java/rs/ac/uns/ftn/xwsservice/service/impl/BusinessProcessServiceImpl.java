package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.ApiRequestException;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.*;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.BusinessProcessService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BusinessProcessServiceImpl implements BusinessProcessService {

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PoslovniProces> getAllProcesses() throws Exception {
        return businessProcessRepository.findAll();
    }

    @Override
    public PoslovniProces getProcessByPublicationId(String id) throws Exception {
        return businessProcessRepository.findByPublicationId(id);
    }

    public PoslovniProces getProcess(String id) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(id);

        if (process == null) {
            throw new ResourceNotFoundException("Process with ID " + id + " doesn't exist.");
        }

        return process;
    }

    @Override
    public String createNewProcess(String publicationId) throws Exception {
        String processId = UUID.randomUUID().toString();

        PoslovniProces process = new PoslovniProces();
        process.setId(processId);
        process.setFaza(EnumFaza.ZA_REVIZIJU);
        process.setStatusRada(EnumStatusRada.U_PROCESU);
        process.setNaucniRadId(publicationId);

        businessProcessRepository.saveObject(process);
        return processId;
    }

    @Override
    public void addCoverLetterForPublication(String processId, String coverLetterId) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(processId);

        if (process == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        process.setPropratnoPismoId(coverLetterId);
        businessProcessRepository.saveObject(process);
    }

    @Override
    public void addReviewersToProcess(String processId, List<String> users) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(processId);

        if (process == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        if (process.getRecenzenti() == null) {
            process.setRecenzenti(new PoslovniProces.Recenzenti());
        }

        List<CTRecenzent> reviewers = process.getRecenzenti().getRecenzent();

        if (!checkIfAllUsersExistInDatabase(users)) {
            throw new ResourceNotFoundException("List of reviewers ID's is not valid. Some of them don't exist in database.");
        }

        for (String userId : users) {
            User user = userRepository.findById(Long.valueOf(userId)).get();

            // TODO: Poslati mejl korisniku da je prihvati/odbije recenziranje ovog rada (procesa)
            CTRecenzent reviewer = new CTRecenzent();
            reviewer.setStatus(EnumStatusRecenziranja.CEKANJE);
            reviewer.setRecenzentID(userId);
            reviewers.add(reviewer);
        }

        businessProcessRepository.saveObject(process);
    }

    private boolean checkIfAllUsersExistInDatabase(List<String> users) {
        for (String userId : users) {
            if (!userRepository.findById(Long.valueOf(userId)).isPresent()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void changeReviewRequestStatus(String processId, EnumStatusRecenziranja status) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(processId);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = currentUser.getId().toString();

        if (process.getRecenzenti() == null) {
            process.setRecenzenti(new PoslovniProces.Recenzenti());
        }

        List<CTRecenzent> reviewers = process.getRecenzenti().getRecenzent();

        CTRecenzent reviewer = reviewers.stream()
                .filter(r -> r.getRecenzentID().equals(userId)).findFirst().orElse(null);

        if (reviewer == null) {
            throw new ApiRequestException("You are not on this list for review.");
        }

        if (!reviewer.getStatus().equals(EnumStatusRecenziranja.CEKANJE)) {
            throw new ApiRequestException("You already decided about this review request.");
        }

        reviewer.setStatus(status);
        businessProcessRepository.saveObject(process);
    }

    @Override
    public void changeProcessPhase(String processId, String phase) throws Exception {
        String processXmlData = businessProcessRepository.findById(processId);

        if (processXmlData == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        EnumFaza newProcessPhase = EnumFaza.valueOf(phase);
        // TODO: Kada se uradi update metoda baze podataka ovde treba promeniti fazu koja je pristigla sa frontenda
    }

    @Override
    public void changeProcessStatus(String processId, EnumStatusRada status) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(processId);

        if (process == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        if (!process.getStatusRada().equals(EnumStatusRada.U_PROCESU)) {
            throw new ApiRequestException("You can't change this publications status, it was already changed.");
        }

        process.setStatusRada(status);
        businessProcessRepository.saveObject(process);
    }
}
