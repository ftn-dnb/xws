package rs.ac.uns.ftn.xwsservice.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;

public class JSONConverter {

    private JSONConverter() {
    }

    public static String xmlToJson(String xmlData) {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
            return xmlJSONObj.toString(4);
        } catch (JSONException e) {
            throw new OperationFailedException("Error while extracting metadata to JSON");
        }
    }
}
