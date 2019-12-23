package rs.ac.uns.ftn.xwsservice.service.impl;

import org.apache.fop.apps.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.service.XSLFOService;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Service
public class XSLFOServiceImpl implements XSLFOService {

    @Autowired
    private FopFactory fopFactory;

    @Autowired
    private TransformerFactory transformerFactory;

    @Override
    public void transform(String xmlData, String xslfoFilePath, String outputFilePath) throws Exception {
        // Point to the XSL-FO file
        File xslFile = new File(xslfoFilePath);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        StreamSource source = new StreamSource(new StringReader(xmlData));

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        if (!outputFilePath.endsWith(".pdf"))
            outputFilePath += ".pdf";

        File pdfFile = new File(outputFilePath);
        if (!pdfFile.getParentFile().exists()) {
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());
        out.close();
    }
}
