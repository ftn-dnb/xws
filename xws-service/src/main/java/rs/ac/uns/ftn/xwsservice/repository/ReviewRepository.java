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
import rs.ac.uns.ftn.xwsservice.model.Recenzija;
import rs.ac.uns.ftn.xwsservice.service.MarshallerService;
import rs.ac.uns.ftn.xwsservice.service.UnmarshallerService;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {

    @Value("${xml.collectionId.review}")
    private String collectionId;

    @Value("${xml.namespace.review}")
    private String TARGET_NAMESPACE;

    @Autowired
    private MarshallerService marshallerService;

    @Autowired
    private UnmarshallerService unmarshallerService;

    public String saveObject(Recenzija recenzija) throws Exception {
        String xmlData = marshallerService.marshal(recenzija);
        ExistSave.save(collectionId, recenzija.getId(), xmlData);
        return recenzija.getId();
    }

    public String save(String xml, String id) throws Exception {
        ExistSave.save(collectionId, id, xml);
        return id;
    }

    public String findById(String id) throws Exception {
        String xPathSelector = String.format("//Recenzija[@id='%s']", id);
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

    public Recenzija findObjectById(String id) throws Exception {
        String xmlData = this.findById(id);

        if (xmlData == null) {
            return null;
        }

        return (Recenzija) unmarshallerService.unmarshal(xmlData);
    }

    public List<Recenzija> findAll() throws Exception {
        String xPathSelector = "//PoslovniProces";
        ResourceSet resultSet = ExistRetrieve.executeXPathExpression(collectionId, xPathSelector, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        List<Recenzija> processes = new ArrayList<>();

        while (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            processes.add((Recenzija) unmarshallerService.unmarshal(res.getContent().toString()));
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return processes;
    }
}
