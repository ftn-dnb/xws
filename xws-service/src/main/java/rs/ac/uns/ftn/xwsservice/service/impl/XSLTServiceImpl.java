package rs.ac.uns.ftn.xwsservice.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.service.XSLTService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Service
public class XSLTServiceImpl implements XSLTService {


    @Override
    public void transform(String xmlData, String xsltFilePath, String outputFilePath) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xsltFilePath));
        Transformer transformer = null;

        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new OperationFailedException("Error while creating XSLT transformer object.");
        }

        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        Source text = new StreamSource(new StringReader(xmlData));

        if (!outputFilePath.endsWith(".html"))
            outputFilePath += ".html";

        try {
            File htmlFile = new File(outputFilePath);

            if (!htmlFile.getParentFile().exists()) {
                htmlFile.getParentFile().mkdir();
            }

            transformer.transform(text, new StreamResult(htmlFile));
        } catch (TransformerException e) {
            throw new OperationFailedException("XSLT error while transforming the document with " + xsltFilePath);
        }
    }

    @Override
    public void transformRDF(String xmlData, String xsltFIlePath, String outputPath) {
        String path = "./src/main/resources/static/data.xml";
        try{
            FileUtils.writeStringToFile(new File(path), xmlData);
            StreamSource streamSource = new StreamSource(new File(xsltFIlePath));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = null;

            try {
                transformer = factory.newTransformer(streamSource);
            } catch (TransformerConfigurationException e) {
                throw new OperationFailedException("Error while creating XSLT transformer object.");
            }

            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource xmlSource = new DOMSource(buildDocument(path));
            StreamResult result = new StreamResult(new FileOutputStream(outputPath));

            try{
                transformer.transform(xmlSource, result);
            }catch(TransformerException e){
                throw new OperationFailedException("XSLT error while transforming the document with " + xsltFIlePath);
            }

        }catch (IOException e){
        }
    }

    private static org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }
}
