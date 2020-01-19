package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;
import rs.ac.uns.ftn.xwsservice.service.UnmarshallerService;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Service
public class UnmarshallerServiceImpl implements UnmarshallerService {

    @Autowired
    private Unmarshaller unmarshaller;

    @Override
    public Object unmarshal(String xmlSource) {
        try {
            return unmarshaller.unmarshal(new ByteArrayInputStream(xmlSource.getBytes(StandardCharsets.UTF_8)));
        } catch (JAXBException e) {
            throw new OperationFailedException("Failed to convert XML source to object representation");
        }
    }
}
