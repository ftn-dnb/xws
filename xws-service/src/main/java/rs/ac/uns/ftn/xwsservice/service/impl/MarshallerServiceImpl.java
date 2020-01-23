package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.service.MarshallerService;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class MarshallerServiceImpl implements MarshallerService {

    @Autowired
    private Marshaller marshaller;

    @Override
    public String marshal(Object data) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        try {
            marshaller.marshal(data, result);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new OperationFailedException("There was an error while converting object to xml string");
        }

        return new String(result.toByteArray(), StandardCharsets.UTF_8);
    }
}
