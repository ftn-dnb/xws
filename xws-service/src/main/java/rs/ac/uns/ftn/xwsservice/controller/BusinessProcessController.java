package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.xwsservice.service.BusinessProcessService;

@RestController
@RequestMapping("/api/process")
public class BusinessProcessController {

    @Autowired
    private BusinessProcessService businessProcessService;

//    @PostMapping
//    public ResponseEntity createNewProcess(@RequestBody CreateProcessDTO data) throws Exception {
//        businessProcessService.createNewProcess(data);
//        return ResponseEntity.ok().build();
//    }
}
