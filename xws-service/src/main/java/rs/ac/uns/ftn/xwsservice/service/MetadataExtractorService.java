package rs.ac.uns.ftn.xwsservice.service;

import java.io.InputStream;

public interface MetadataExtractorService {

    String extractMetadataToRdf(InputStream in);
    String extractMetadataToJson(String xmlData);
    String extractMetadataToRdf(String xmlData);
}
