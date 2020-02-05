package rs.ac.uns.ftn.xwsservice.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PublicationIdUtil {

    public static String addPublicationId(String xml, String id) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new InputSource(new StringReader(xml)));

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList)xpath.evaluate("//NaucniRad",
                doc, XPathConstants.NODESET);

        ((Element)nodes.item(0)).setAttribute("id", id);

        StringWriter writer = new StringWriter();

        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.getBuffer().toString();
    }

    public static String addDates(String xml) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new InputSource(new StringReader(xml)));

        XPath xpath = XPathFactory.newInstance().newXPath();

        NodeList nodes = (NodeList)xpath.evaluate("//DatumPrijema",
                doc, XPathConstants.NODESET);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        //((Element)nodes.item(0)).setNodeValue(dtf.format(now));
        Element e = (Element)nodes.item(0);
        e.getFirstChild().setNodeValue(dtf.format(now));

        nodes = (NodeList)xpath.evaluate("//DatumPrihvatanja",
                doc, XPathConstants.NODESET);

        e = (Element)nodes.item(0);
        e.getFirstChild().setNodeValue("");
        StringWriter writer = new StringWriter();

        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.getBuffer().toString();
    }

    public static String addAuthorId(String xml, String userId) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new InputSource(new StringReader(xml)));

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList)xpath.evaluate("//Autori/Autor[1]",
                doc, XPathConstants.NODESET);

        ((Element)nodes.item(0)).setAttribute("id", userId);

        StringWriter writer = new StringWriter();

        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.getBuffer().toString();
    }

}
