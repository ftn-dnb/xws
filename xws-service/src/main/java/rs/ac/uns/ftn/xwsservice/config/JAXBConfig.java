package rs.ac.uns.ftn.xwsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.xwsservice.utils.NSPrefixMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Configuration
public class JAXBConfig {

    @Bean
    public Unmarshaller createUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xwsservice.model");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller;
    }

    @Bean
    public Marshaller createMarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xwsservice.model");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return marshaller;
    }
}
