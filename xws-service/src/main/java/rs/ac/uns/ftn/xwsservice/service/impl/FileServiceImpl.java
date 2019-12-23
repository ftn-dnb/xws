package rs.ac.uns.ftn.xwsservice.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.xwsservice.service.FileService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public ByteArrayInputStream readPdfFile(String path) {
        if (!path.endsWith(".pdf"))
            path += ".pdf";

        File pdfFile = new File(path);

        if (!pdfFile.exists()) {
            throw new ResourceNotFoundException("PDF file with ID '" + path + "' doesn't exist.");
        }

        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailedException("Error while reading PDF file with ID '" + path + "'");
        }
    }

    @Override
    public String readHtmlFile(String path) {
        if (!path.endsWith(".html"))
            path += ".html";

        File htmlFile = new File(path);

        if (!htmlFile.exists()) {
            throw new ResourceNotFoundException("HTML file with ID '" + path + "' doesn't exist.");
        }

        try {
            return FileUtils.readFileToString(htmlFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailedException("Error while reading HTML file with ID '" + path + "'");
        }
    }
}
