package rs.ac.uns.ftn.xwsservice.service;

public interface XSLTService {

    void transform(String xmlData, String xsltFilePath, String outputPath);
}
