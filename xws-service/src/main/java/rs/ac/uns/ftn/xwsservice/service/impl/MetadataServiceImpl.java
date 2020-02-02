package rs.ac.uns.ftn.xwsservice.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.dto.request.SearchMetadataDTO;
import rs.ac.uns.ftn.xwsservice.service.MetadataService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MetadataServiceImpl implements MetadataService {

    private static String RDF_XML = "RDF/XML";
    private static String NTRIPLES = "N-TRIPLES";
    private static String UPDATE_TEMPLATE_NAMED_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";
    private static String GRAPH_URI = "/naucniRadovi/metadata";

    @Value("${jenna.endpoint}")
    private  String endpoint;

    @Value("${jenna.dataset}")
    private  String dataSet;

    @Value("${jenna.data}")
    private String data;

    @Value("${jenna.update}")
    private  String update;

    @Value("${jenna.query}")
    private String query;

    @Value("${sparql.path.querySelect}")
    private String querySelectPath;

    @Override
    public void metadataWrite(String filePath) {

        String dataEndpoint = String.join("/", endpoint, dataSet, data);
        String updateEndpoint = String.join("/", endpoint,dataSet,update);

        // Creates a default model
        Model model = ModelFactory.createDefaultModel();
        model.read(filePath);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, NTRIPLES);

        model.write(System.out, RDF_XML);

        UpdateRequest request = UpdateFactory.create() ;

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, updateEndpoint);
        processor.execute();

        // Creating the first named graph and updating it with RDF data
        String sparqlUpdate = insertData(dataEndpoint + GRAPH_URI, new String(out.toByteArray()));

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        processor = UpdateExecutionFactory.createRemote(update, updateEndpoint);
        processor.execute();
    }

    @Override
    public List<String> metaDataSelect(SearchMetadataDTO dto) {
        String dataEndpoint = String.join("/", endpoint, dataSet, data);
        String queryEndpoint = String.join("/",endpoint,dataSet,query);
        String sparqlQuery = null;

        List<String> result = new ArrayList<>();
        try {

            sparqlQuery = String.format(readFile(querySelectPath, StandardCharsets.UTF_8),
                   dto.getKeyword(),dto.getTitle(),dto.getLanguage(),dto.getType(),
                    dto.getAutor1(), dto.getAutor2(),
                    dto.getStartDatePublished(),dto.getEndDatePublished(),dto.getStartDatePublished(),
                    dto.getEndDateCreated());
        }catch (IOException e){

        }
        System.out.println(sparqlQuery);

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(queryEndpoint, sparqlQuery);

        // Query the SPARQL endpoint, iterate over the result set...
        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;

        while (results.hasNext()) {

            // A single answer from a SELECT query
            QuerySolution querySolution = results.next();
            Iterator<String> variableBindings = querySolution.varNames();

            // Retrieve variable bindings
            while (variableBindings.hasNext()) {

                varName = variableBindings.next();
                varValue = querySolution.get(varName);

                //System.out.println(varName + ": " + varValue);
                if(varName.equals("rad")){
                    String last = varValue.toString().substring(varValue.toString().lastIndexOf("/") + 1);
                    System.out.println("My id: " + last);
                    result.add(last);
                }
            }
            System.out.println();
        }

        return result;
    }

    public static String insertData(String graphURI, String ntriples) {
        return String.format(UPDATE_TEMPLATE_NAMED_GRAPH, graphURI, ntriples);
    }

    public static String readFile(String path, Charset encoding) throws IOException{
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded,encoding);
    }
}
