package rs.ac.uns.ftn.xwsservice.service;

import java.io.ByteArrayInputStream;

public interface FileService {

    ByteArrayInputStream readPdfFile(String path);
    String readHtmlFile(String path);
}
