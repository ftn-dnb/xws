package rs.ac.uns.ftn.xwsservice.service;

public interface XSLTService {

    void transform(String xmlData, String xsltFilePath, String outputPath);
    void transformRDF(String xmlData, String xslrFIlePath, String outputPath);
}
