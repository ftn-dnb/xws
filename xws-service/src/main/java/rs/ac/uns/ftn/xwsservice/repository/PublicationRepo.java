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
public class PublicationRepo {

    @Autowired
    private UnmarshallerService unmarshallerService;

    @Autowired
    private MarshallerService marshallerService;

    @Value("${xml.collectionId.publication}")
    private String collectionId;

    @Value("${xml.namespace.publication}")
    private String TARGET_NAMESPACE;

    public String save(String xml, String id) throws Exception {
        ExistSave.save(collectionId, id, xml);
        return id;
    }

    public String saveObject(NaucniRad publication) throws Exception {
        String xmlData = marshallerService.marshal(publication);
        ExistSave.save(collectionId, publication.getId(), xmlData);
        return publication.getId();
    }

    public NaucniRad findObjectById(String id) throws Exception {
        String xmlData = this.findById(id);
        return (NaucniRad) unmarshallerService.unmarshal(xmlData);
    }

    public String findById(String id) throws Exception {
        String xPathSelector = String.format("//NaucniRad[@id='%s']", id);
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

    public List<NaucniRad> findByUser(String userId) throws Exception {
        String xPathSelector = String.format("//NaucniRad[NaslovnaStrana/Autori/Autor[@id='%s']]", userId);
        ResourceSet resultSet = ExistRetrieve.executeXPathExpression(collectionId, xPathSelector, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        List<NaucniRad> pubs = new ArrayList<>();

        while (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            pubs.add((NaucniRad) unmarshallerService.unmarshal(res.getContent().toString()));
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return pubs;
    }

    public List<NaucniRad> filterPublications(String filterText) throws Exception {
        String xPathSelector = String.format("//NaucniRad[*//*[contains(text(),'%s')]]", filterText);
        //String xPathSelector = String.format("//NaucniRad", filterText);
        ResourceSet resultSet = ExistRetrieve.executeXPathExpression(collectionId, xPathSelector, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        List<NaucniRad> pubs = new ArrayList<>();

        while (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            pubs.add((NaucniRad) unmarshallerService.unmarshal(res.getContent().toString()));
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return pubs;
    }

}
