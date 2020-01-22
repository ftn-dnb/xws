package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
