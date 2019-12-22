package rs.ac.uns.ftn.xwsservice.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.service.FileService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {

    @Value("${xslfo.path.output-folder}")
    private String pdfOutputFolder;

    @Value("${xslt.path.output-folder}")
    private String htmlOutputFolder;

    @Override
    public ByteArrayInputStream readPdfFile(String id) {
        File pdfFile = new File(this.pdfOutputFolder + id + ".pdf");

        if (!pdfFile.exists()) {
            throw new OperationFailedException("PDF file with ID '" + id + "' doesn't exist.");
        }

        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailedException("Error while reading PDF file with ID '" + id + "'");
        }
    }

    @Override
    public String readHtmlFile(String id) {
        File htmlFile = new File(this.htmlOutputFolder + id + ".html");

        if (!htmlFile.exists()) {
            throw new OperationFailedException("HTML file with ID '" + id + "' doesn't exist.");
        }

        try {
            return FileUtils.readFileToString(htmlFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailedException("Error while reading HTML file with ID '" + id + "'");
        }
    }
}
