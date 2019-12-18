package rs.ac.uns.ftn.xwsservice.dao;

import org.exist.xmldb.EXistResource;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.ac.uns.ftn.xwsservice.model.Bookstore;
import rs.ac.uns.ftn.xwsservice.utils.AuthenticationUtilities;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;

public class UnmarshallDao {

    public String retrieveUnmarshalledObjects(String filePath) throws Exception {
        return retrieve(AuthenticationUtilities.loadProperties(), filePath);
    }

    private String retrieve(AuthenticationUtilities.ConnectionProperties conn, String filePath) throws Exception {

        // initialize collection and document identifiers
        String collectionId = null;
        String documentId = null;

        collectionId = "/db/sample/library";
        documentId = filePath;

        // initialize database driver
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;

        String result = null;

        try {
            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            String c = conn.uri + collectionId;
            col = DatabaseManager.getCollection(c);
            col.setProperty(OutputKeys.INDENT, "yes");

            System.out.println("[INFO] Retrieving the document: " + documentId);
            res = (XMLResource)col.getResource(documentId);

            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
            } else {

                System.out.println("[INFO] Binding XML resouce to an JAXB instance: ");
                JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xwsservice.model");

                Unmarshaller unmarshaller = context.createUnmarshaller();

                Node node = res.getContentAsDOM();

                Bookstore bookstore = (Bookstore) unmarshaller.unmarshal(node);

                result = bookstore.toString();

            }
        } finally {

            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return result;

    }
}
