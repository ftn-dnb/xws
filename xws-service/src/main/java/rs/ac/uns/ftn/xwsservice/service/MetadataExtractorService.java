package rs.ac.uns.ftn.xwsservice.service;

public interface MetadataExtractorService {

    String extractMetadataToRdf(String xmlData);
    String extractMetadataToJson(String xmlData);
}
