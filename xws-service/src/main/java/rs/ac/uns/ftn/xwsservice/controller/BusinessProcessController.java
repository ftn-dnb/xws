package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.response.BusinessProcessDTO;
import rs.ac.uns.ftn.xwsservice.mappers.BusinessProcessMapper;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
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
        List<PoslovniProces> processes = businessProcessService.getAllProcesses();
        return new ResponseEntity<>(BusinessProcessMapper.toListDto(processes), HttpStatus.OK);
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

    @PutMapping("/change-phase/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity changeProcessPhase(@PathVariable String processId, @RequestBody String phase) throws Exception {
        businessProcessService.changeProcessPhase(processId, phase);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-status/{processId}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity changeProcessStatus(@PathVariable String processId, @RequestBody Boolean status) throws Exception {
        businessProcessService.changeProcessStatus(processId, status);
        return ResponseEntity.ok().build();
    }
}
