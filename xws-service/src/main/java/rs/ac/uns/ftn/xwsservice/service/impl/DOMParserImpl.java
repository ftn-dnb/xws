package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.StringReader;

@Service
public class DOMParserImpl {

    @Value("${xsd.path.coverletter}")
    private String coverLetterXmlSchema;

    @Autowired
    private DocumentBuilderFactory documentBuilderFactory;

    @Autowired
    private SchemaFactory schemaFactory;


    /**
     *  Proverava da li je prosledjeni XML sadrzaj validan. Provera se vrsi uz
     *  odgovarajucu XML Schemu koja se prosledjuje.
     *  Ako je sve u redu vraca se kreirani dokument koji se dalje moze parsirati.
     */
    public Document isXmlDataValid(String xmlData, String xmlSchemaFilePath) {
        try {
            Schema xmlSchema = this.loadXmlSchema(xmlSchemaFilePath);
            documentBuilderFactory.setSchema(xmlSchema);

            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlData)));
            return document;
        } catch(Exception e) {
            throw new OperationFailedException("Error while parsing XML document against this schema: " + xmlSchemaFilePath);
        }
    }

    /**
     *  Ucitavanje XML Schema fajla.
     */
    private Schema loadXmlSchema(String filePath) {
        try {
            return schemaFactory.newSchema(new File(filePath));
        } catch (SAXException e) {
            throw new OperationFailedException("Error while parsing XML schema from path: " + filePath);
        }
    }

    /**
     *  Metoda parsira propratno pismo is XML stringa u Java objekat.
     */
    // TODO: promeni povratni tip kasnije
    public void parseCoverLetter(String xmlData) {
        Document document = this.isXmlDataValid(xmlData, this.coverLetterXmlSchema);

        // TODO: dodati return objekta koji je rezultat parsiranja
        return;
    }
}
