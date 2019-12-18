package rs.ac.uns.ftn.xwsservice.service;

import java.util.ArrayList;

public interface XMLService {

    String retrieveObjectString(String fileName);
    ArrayList<String> retrieveXPath(String expr);
}
