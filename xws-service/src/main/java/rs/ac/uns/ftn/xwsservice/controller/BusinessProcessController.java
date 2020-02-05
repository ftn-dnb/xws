package rs.ac.uns.ftn.xwsservice.controller;

import org.exist.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.response.BusinessProcessDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewRequestDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.ReviewerDTO;
import rs.ac.uns.ftn.xwsservice.mappers.BusinessProcessMapper;
import rs.ac.uns.ftn.xwsservice.mappers.UserMapper;
import rs.ac.uns.ftn.xwsservice.model.EnumStatusRada;
import rs.ac.uns.ftn.xwsservice.model.EnumStatusRecenziranja;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
import rs.ac.uns.ftn.xwsservice.model.User;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.service.BusinessProcessService;

import java.util.List;

@RestController
@RequestMapping("/api/process")
public class BusinessProcessController {

    @Autowired
    private BusinessProcessService businessProcessService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<BusinessProcessDTO>> getAllProcesses() throws Exception {
        List<BusinessProcessDTO> processes = businessProcessService.getAllProcessesDTO();
        return new ResponseEntity<>(processes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<BusinessProcessDTO> getProcess(@PathVariable String id) throws Exception {
        PoslovniProces process = businessProcessService.getProcess(id);
        return new ResponseEntity<>(BusinessProcessMapper.toDto(process), HttpStatus.OK);
    }

    @GetMapping("/publication/{id}")
    public ResponseEntity<BusinessProcessDTO> getProcessByPublicationId(@PathVariable String id) throws Exception {
        PoslovniProces process = businessProcessService.getProcessByPublicationId(id);
        return new ResponseEntity<>(BusinessProcessMapper.toDto(process), HttpStatus.OK);
    }

    /**
     * Dodavanje recenzenata u poslovni proces za neki naucni rad.
     * Kao parametar se salje lista stringova sa ID-evima korisnika koji ce biti recenzenti za taj rad.
     */
    @PutMapping("/add-reviewers/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity addReviewers(@PathVariable String processId, @RequestBody List<String> users) throws Exception {
        businessProcessService.addReviewersToProcess(processId, users);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recommend-reviewers/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<List<ReviewerDTO>> recommendReviewers(@PathVariable String processId) throws Exception {
        List<ReviewerDTO> reviewers = businessProcessService.recommendReviewers(processId);
        return new ResponseEntity<>(reviewers, HttpStatus.OK);
    }

    @GetMapping("/my-review-requests")
    public ResponseEntity<List<ReviewRequestDTO>> getMyReviewRequests() throws Exception {
        return new ResponseEntity<>(businessProcessService.getMyReviewRequests(), HttpStatus.OK);
    }

    @GetMapping("/accept-review-request/{processId}")
    public ResponseEntity acceptReviewRequest(@PathVariable String processId) throws Exception {
        businessProcessService.changeReviewRequestStatus(processId, EnumStatusRecenziranja.PRIHVACEN);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/decline-review-request/{processId}")
    public ResponseEntity declineReviewRequest(@PathVariable String processId) throws Exception {
        businessProcessService.changeReviewRequestStatus(processId, EnumStatusRecenziranja.ODBIJEN);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-phase/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity changeProcessPhase(@PathVariable String processId, @RequestBody String phase) throws Exception {
        businessProcessService.changeProcessPhase(processId, phase);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accept-publication/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity acceptPublication(@PathVariable String processId) throws Exception {
        businessProcessService.changeProcessStatus(processId, EnumStatusRada.PRIHVACEN);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/decline-publication/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity declinePublication(@PathVariable String processId) throws Exception {
        businessProcessService.changeProcessStatus(processId, EnumStatusRada.ODBIJEN);
        return ResponseEntity.ok().build();
    }
}
