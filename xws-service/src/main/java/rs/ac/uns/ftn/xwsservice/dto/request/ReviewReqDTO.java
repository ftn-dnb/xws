package rs.ac.uns.ftn.xwsservice.dto.request;

public class ReviewReqDTO {

    private String xmlData;
    private String processId;

    public ReviewReqDTO() {}

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}

