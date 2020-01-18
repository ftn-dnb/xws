package rs.ac.uns.ftn.xwsservice.exist;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;
import rs.ac.uns.ftn.xwsservice.utils.AuthenticationUtilities;

import java.util.ArrayList;

public class XPathDao {

    private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/xwsservice/model";

    public ArrayList<String> retrieveXPathExpressionResults(String expr) throws Exception {
        return retrieve(AuthenticationUtilities.loadProperties(), expr);
    }

    private ArrayList<String> retrieve(AuthenticationUtilities.ConnectionProperties conn, String expr) throws Exception {

        // initialize collection and document identifiers
        String collectionId = null;

        collectionId = "/db/sample/library";

        System.out.println("\t- collection ID: " + collectionId);

        // initialize database driver
        System.out.println("[INFO] Loading driver class: " + conn.driver);
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        ArrayList<String> resultSet = new ArrayList<>();

        try {

            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(conn.uri + collectionId);

            // get an instance of xpath query service
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");

            // make the service aware of namespaces, using the default one
            xpathService.setNamespace("", TARGET_NAMESPACE);


            // execute xpath expression
            System.out.println("[INFO] Invoking XPath query service for: " + expr);
            ResourceSet result = xpathService.query(expr);

            // handle the results
            System.out.println("[INFO] Handling the results... ");

            ResourceIterator i = result.getIterator();
            Resource res = null;



            while(i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    resultSet.add((String) res.getContent());

                } finally {

                    // don't forget to cleanup resources
                    try {
                        ((EXistResource)res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }

        } finally {

            // don't forget to cleanup
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }


        return resultSet;
    }
}
