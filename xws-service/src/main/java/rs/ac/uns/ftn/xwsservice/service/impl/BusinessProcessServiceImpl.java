package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        String processXmlData = businessProcessRepository.findById(processId);

        // TODO: Treba pozvati update metodu nad procitanim biznis procesom i dodati prosledjeni
        // coverLetterId u polje 'PropratnoPismoId'
        // Ovo se moze zavrsiti tek kada se implementira UPDATE metod nad bazom podataka.
    }
}
