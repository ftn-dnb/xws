package rs.ac.uns.ftn.xwsservice.config;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FopFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.IOException;

@Configuration
public class XSLFOConfiguration {

    @Value("${xslfo.fop.fileconfig}")
    private String fopFileConfigPath;

    @Bean
    public FopFactory fopFactory() throws IOException, SAXException {
        return FopFactory.newInstance(new File(fopFileConfigPath));
    }

    @Bean
    public TransformerFactory transformerFactory() {
        return new TransformerFactoryImpl();
    }
}
