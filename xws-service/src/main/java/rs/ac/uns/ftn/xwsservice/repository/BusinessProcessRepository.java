package rs.ac.uns.ftn.xwsservice.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.ac.uns.ftn.xwsservice.exist.ExistRetrieve;
import rs.ac.uns.ftn.xwsservice.exist.ExistSave;
import rs.ac.uns.ftn.xwsservice.model.NaucniRad;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;
import rs.ac.uns.ftn.xwsservice.service.MarshallerService;
import rs.ac.uns.ftn.xwsservice.service.UnmarshallerService;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BusinessProcessRepository {

    @Value("${xml.collectionId.business-process}")
    private String collectionId;

    @Value("${xml.namespace.business-process}")
    private String TARGET_NAMESPACE;

    @Autowired
    private MarshallerService marshallerService;

    @Autowired
    private UnmarshallerService unmarshallerService;


    public String saveObject(PoslovniProces process) throws Exception {
        String xmlData = marshallerService.marshal(process);
        ExistSave.save(collectionId, process.getId(), xmlData);
        return process.getId();
    }

    public String save(String xml, String id) throws Exception {
        ExistSave.save(collectionId, id, xml);
        return id;
    }

    public String findById(String id) throws Exception {
        String xPathSelector = String.format("//PoslovniProces[@id='%s']", id);
        ResourceSet resultSet = ExistRetrieve.executeXPathExpression(collectionId, xPathSelector, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        String retVal = null;

        if (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            retVal = res.getContent().toString();
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return retVal;
    }

    public PoslovniProces findObjectById(String id) throws Exception {
        String xmlData = this.findById(id);

        if (xmlData == null) {
            return null;
        }

        return (PoslovniProces) unmarshallerService.unmarshal(xmlData);
    }

    public List<PoslovniProces> findAll() throws Exception {
        String xPathSelector = "//PoslovniProces";
        ResourceSet resultSet = ExistRetrieve.executeXPathExpression(collectionId, xPathSelector, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        List<PoslovniProces> processes = new ArrayList<>();

        while (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            processes.add((PoslovniProces) unmarshallerService.unmarshal(res.getContent().toString()));
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return processes;
    }

    public PoslovniProces findByPublicationId(String id) throws Exception {
        String xPathSelector = String.format("//PoslovniProces[NaucniRadId[text()='%s']]", id);
        ResourceSet resultSet = ExistRetrieve.executeXPathExpression(collectionId, xPathSelector, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        PoslovniProces process = null;

        while (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            process = (PoslovniProces)unmarshallerService.unmarshal(res.getContent().toString());
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return process;
    }
}
