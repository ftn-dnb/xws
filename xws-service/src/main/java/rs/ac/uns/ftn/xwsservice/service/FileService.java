package rs.ac.uns.ftn.xwsservice.service;

import java.io.ByteArrayInputStream;

public interface FileService {

    ByteArrayInputStream readPdfFile(String id);
    String readHtmlFile(String id);
}
