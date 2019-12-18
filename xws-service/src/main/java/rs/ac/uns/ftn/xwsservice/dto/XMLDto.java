package rs.ac.uns.ftn.xwsservice.dto;

public class XMLDto {

    private String xmlFile;
    private String xPathExp;

    public XMLDto() {
        super();
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    public String getxPathExp() {
        return xPathExp;
    }

    public void setxPathExp(String xPathExp) {
        this.xPathExp = xPathExp;
    }
}
