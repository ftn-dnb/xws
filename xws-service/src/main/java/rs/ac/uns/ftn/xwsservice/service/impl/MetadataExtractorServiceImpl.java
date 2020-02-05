package rs.ac.uns.ftn.xwsservice.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.service.MetadataExtractorService;
import rs.ac.uns.ftn.xwsservice.utils.FileReader;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class MetadataExtractorServiceImpl implements MetadataExtractorService {

    @Value("${grddl.path.transformation}")
    private String grddlXslTransformationFilePath;

    @Value("${grddl.path.output-folder}")
    private String grddlOutputFolder;

    @Override
    public String extractMetadataToRdf(InputStream in, String id) {
        StreamSource transformSource = new StreamSource(new File(grddlXslTransformationFilePath));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer grddlTransformer = null;

        try {
            grddlTransformer = factory.newTransformer(transformSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new OperationFailedException("Failed to initialize GRDDL transformer");
        }

        grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        String outputFilePath = this.grddlOutputFolder + id + ".xml";

        //StreamSource source = new StreamSource(new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8)));
        StreamSource source = new StreamSource(in);
        StreamResult result = null;

        try {
            result = new StreamResult(new FileOutputStream(outputFilePath));
        } catch (FileNotFoundException e) {
            throw new OperationFailedException("Error while creating output file");
        }

        try {
            grddlTransformer.transform(source, result);
        } catch (TransformerException e) {
            throw new OperationFailedException("Error while extracting metadata to RDF");
        }

        String rdfXmlResult = FileReader.readFromFile(outputFilePath);
        //File outputFile = new File(outputFilePath);
        //outputFile.delete();

        return outputFilePath;
    }

    @Override
    public String extractMetadataToRdf(String xmlData) {
        StreamSource transformSource = new StreamSource(new File(grddlXslTransformationFilePath));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer grddlTransformer = null;

        try {
            grddlTransformer = factory.newTransformer(transformSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new OperationFailedException("Failed to initialize GRDDL transformer");
        }

        grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        String outputFilePath = this.grddlOutputFolder + UUID.randomUUID().toString() + ".xml";

        StreamSource source = new StreamSource(new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8)));
        //StreamSource source = new StreamSource(in);
        StreamResult result = null;

        try {
            result = new StreamResult(new FileOutputStream(outputFilePath));
        } catch (FileNotFoundException e) {
            throw new OperationFailedException("Error while creating output file");
        }

        try {
            grddlTransformer.transform(source, result);
        } catch (TransformerException e) {
            throw new OperationFailedException("Error while extracting metadata to RDF");
        }

        String rdfXmlResult = FileReader.readFromFile(outputFilePath);
        File outputFile = new File(outputFilePath);
        outputFile.delete();

        return rdfXmlResult;
    }


    /**
     * UNUSED
     */
    @Override
    public String extractMetadataToJson(String xmlData) {
        String rdfXml = this.extractMetadataToRdf(xmlData);

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(rdfXml);
            return xmlJSONObj.toString(4);
        } catch (JSONException e) {
            throw new OperationFailedException("Error while extracting metadata to JSON");
        }
    }
}
