package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.model.EnumFaza;
import rs.ac.uns.ftn.xwsservice.model.EnumStatusRada;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
import rs.ac.uns.ftn.xwsservice.repository.BusinessProcessRepository;
import rs.ac.uns.ftn.xwsservice.service.BusinessProcessService;

import java.util.List;
import java.util.UUID;

@Service
public class BusinessProcessServiceImpl implements BusinessProcessService {

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    @Override
    public List<PoslovniProces> getAllProcesses() throws Exception {
        return businessProcessRepository.findAll();
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
        String processXmlData = businessProcessRepository.findById(processId);

        if (processXmlData == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        // TODO: U listu recenzenata treba dodati id-eve korisnika koji se nalaze u parametru 'users'
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
    public void changeProcessStatus(String processId, boolean status) throws Exception {
        PoslovniProces process = businessProcessRepository.findObjectById(processId);

        if (process == null) {
            throw new ResourceNotFoundException("Process with ID " + processId + " doesn't exist.");
        }

        EnumStatusRada newStatus = (status) ? EnumStatusRada.PRIHVACEN : EnumStatusRada.ODBIJEN;
        process.setStatusRada(newStatus);
        businessProcessRepository.saveObject(process);
    }
}
