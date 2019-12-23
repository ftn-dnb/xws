package rs.ac.uns.ftn.xwsservice.service;

public interface XSLFOService {

    void transform(String xmlData, String xslfoFilePath, String outputFilePath) throws Exception;
}
