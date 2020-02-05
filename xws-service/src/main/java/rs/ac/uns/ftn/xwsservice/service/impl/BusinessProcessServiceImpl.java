package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.dto.response.BusinessProcessDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewRequestDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewerDTO;
import rs.ac.uns.ftn.xwsservice.exception.ApiRequestException;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.*;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.repository.PublicationRepo;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.BusinessProcessService;
import rs.ac.uns.ftn.xwsservice.utils.PublicationIdUtil;
import rs.ac.uns.ftn.xwsservice.utils.Sorting;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusinessProcessServiceImpl implements BusinessProcessService {

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    @Autowired
    private PublicationRepo publicationRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PoslovniProces> getAllProcesses() throws Exception {
        return businessProcessRepository.findAll();
    }

    @Override
    public List<BusinessProcessDTO> getAllProcessesDTO() throws Exception {
        List<PoslovniProces> processes = businessProcessRepository.findAll();
        List<BusinessProcessDTO> processesDto = new ArrayList<>();

        for (PoslovniProces process : processes) {
            NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
            processesDto.add(new BusinessProcessDTO(process, publication));
        }

        return processesDto;
    }

    @Override
    public PoslovniProces getProcessByPublicationId(String id) throws Exception {
        return businessProcessRepository.findByPublicationId(id);
    }

    @Override
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
        process.setFaza(EnumFaza.ZA_RECENZIJU);
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

            if (checkIfReviewerIsAlreadyAdded(userId, reviewers)) {
                throw new ApiRequestException("User with ID " + userId + " is already reviewer on this publication.");
            }

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

    private boolean checkIfReviewerIsAlreadyAdded(String userId, List<CTRecenzent> reviewers) {
        for (CTRecenzent reviewer : reviewers) {
            if (reviewer.getRecenzentID().equals(userId))
                return true;
        }

        return false;
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
        PoslovniProces proces = businessProcessRepository.findObjectById(processId);

        if (proces == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        if (!proces.getStatusRada().equals(EnumStatusRada.U_PROCESU)) {
            throw new ApiRequestException("You can't change process phase.");
        }

        EnumFaza newProcessPhase = EnumFaza.fromValue(phase);
        proces.setFaza(newProcessPhase);
        businessProcessRepository.saveObject(proces);
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

    @Override
    public List<ReviewRequestDTO> getMyReviewRequests() throws Exception {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PoslovniProces> myProcesses = businessProcessRepository.findByReviewerId(currentUser.getId().toString());

        List<ReviewRequestDTO> requests = new ArrayList<>();

        for (PoslovniProces process : myProcesses) {
            NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());
            EnumStatusRecenziranja status = getReviewStatusForUser(currentUser.getId().toString(), process);
            requests.add(new ReviewRequestDTO(process, publication, status.toString()));
        }

        return requests;
    }

    private EnumStatusRecenziranja getReviewStatusForUser(String userId, PoslovniProces process) {
        return process.getRecenzenti().getRecenzent()
                .stream().filter(r -> r.getRecenzentID().equals(userId))
                .findFirst().get().getStatus();
    }

    @Override
    public List<ReviewerDTO> recommendReviewers(String processId) throws Exception {
        PoslovniProces process = this.getProcess(processId);
        NaucniRad publication = publicationRepo.findObjectById(process.getNaucniRadId());

        List<String> keywords = publication.getKljucneReci().getKljucnaRec()
                .stream().map(keyword -> keyword.getValue()).collect(Collectors.toList());

        Map<String, List<String>> usersKeywords = new HashMap<>(); // Key is user id, and values are list of all users keywords

        List<NaucniRad> allPublications = publicationRepo.findAll();

        for (NaucniRad pub : allPublications) {
            // Ignore publication for which we are recommending reviewers
            if (pub.getId().equals(process.getNaucniRadId()))
                continue;

            String authorId = pub.getNaslovnaStrana().getAutori().getAutor().get(0).getId();

            // Ignore author that added this publication
            if (publication.getNaslovnaStrana().getAutori().getAutor().get(0).getId().equals(authorId))
                continue;

            List<String> pubKeywords = pub.getKljucneReci().getKljucnaRec()
                    .stream().map(keyword -> keyword.getValue()).collect(Collectors.toList());

            if (usersKeywords.containsKey(authorId)) {
                usersKeywords.get(authorId).addAll(pubKeywords);
            } else {
                usersKeywords.put(authorId, new ArrayList<>(pubKeywords));
            }
        }

        List<ReviewerDTO> recommendedReviewers = this.findBestReviewers(usersKeywords, keywords);

        return recommendedReviewers;
    }

    private List<ReviewerDTO> findBestReviewers(Map<String, List<String>> authorsKeywords, List<String> pubKeywords) {
        HashMap<String, Integer> numOfUsersKeywords = new HashMap<>(); // Key is user id, value is number of keywords that are the same as in pubKeywords

        for (Map.Entry<String, List<String>> entry : authorsKeywords.entrySet()) {
            int keywordCounter = 0;

            for (String keyword : entry.getValue()) {
                if (pubKeywords.contains(keyword))
                    ++keywordCounter;
            }

            numOfUsersKeywords.put(entry.getKey(), keywordCounter);
        }

        List<ReviewerDTO> reviewers = new ArrayList<>();
        HashMap<String, Integer> sorted = Sorting.sortByValue(numOfUsersKeywords);

        sorted.forEach((userId, score) -> {
            User user = userRepository.findById(Long.valueOf(userId)).get();
            reviewers.add(new ReviewerDTO(user, score));
        });

        return reviewers;
    }

}
