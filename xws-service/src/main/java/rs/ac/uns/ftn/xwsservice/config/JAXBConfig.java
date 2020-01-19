package rs.ac.uns.ftn.xwsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Configuration
public class JAXBConfig {

    @Bean
    public Unmarshaller createUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.xwsservice.model");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller;
    }
}
