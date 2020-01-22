package rs.ac.uns.ftn.xwsservice.utils;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Klasa koja definiše custom pravila mapiranja
 * namespaceova na prefikse.
 *
 */
@XmlTransient
public class NSPrefixMapper extends NamespacePrefixMapper {

    private HashMap<String, String> mappings;

    public NSPrefixMapper() {
        mappings = new LinkedHashMap<String, String>();
        setDefaultMappings();
    }

    protected void setDefaultMappings() {
        clear();
        addMapping("http://www.ftn.uns.ac.rs/xws/tim5", "");
    }

    public void addMapping(String uri, String prefix){
        mappings.put(uri, prefix);
    }

    public String getMapping(String uri){
        return (String)mappings.get(uri);
    }
    public HashMap<String, String> getMappings(){
        return mappings;
    }
    public void clear(){
        mappings.clear();
    }

    /**
     * Metoda vraća preferirani prefiks za zadati namespace.
     */
    public String getPreferredPrefix(String namespaceURI, String suggestion, boolean requirePrefix) {
        String preferredPrefix = getMapping(namespaceURI);
        if(preferredPrefix != null)
            return preferredPrefix;
        return suggestion;
    }

}