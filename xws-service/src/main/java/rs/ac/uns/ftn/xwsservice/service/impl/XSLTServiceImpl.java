package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.service.XSLTService;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;

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
}
