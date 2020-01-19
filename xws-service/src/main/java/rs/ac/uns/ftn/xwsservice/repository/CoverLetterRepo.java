package rs.ac.uns.ftn.xwsservice.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.ac.uns.ftn.xwsservice.exist.ExistRetrieve;
import rs.ac.uns.ftn.xwsservice.exist.ExistSave;

@Repository
public class CoverLetterRepo {

    @Value("${xml.collectionId.cover-letter}")
    private String collectionId;

    @Value("${xml.namespace.cover-letter}")
    private String TARGET_NAMESPACE;

    public String save(String xml, String id) throws Exception {
        ExistSave.save(collectionId, id, xml);
        return id;
    }

    public String findById(String id) throws Exception {
        String xPathSelector = String.format("//PropratnoPismo[@id='%s']", id);
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

}
