package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.XSLTTransformationException;
import rs.ac.uns.ftn.xwsservice.service.XSLTService;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;

@Service
public class XSLTServiceImpl implements XSLTService {

    @Value("${xslt.path.output-folder}")
    private String outputFolder;

    @Override
    public void transform(String xmlData, String xsltFilePath, String outputFileName) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xsltFilePath));
        Transformer transformer = null;

        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new XSLTTransformationException("Error while creating XSLT transformer object.");
        }

        Source text = new StreamSource(new StringReader(xmlData));

        try {
            String outputPath = outputFolder + outputFileName + ".html";
            transformer.transform(text, new StreamResult(new File(outputPath)));
        } catch (TransformerException e) {
            throw new XSLTTransformationException("XSLT error while transforming the document with " + xsltFilePath);
        }
    }
}
