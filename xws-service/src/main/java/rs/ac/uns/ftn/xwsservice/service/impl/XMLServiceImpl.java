package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.dao.UnmarshallDao;
import rs.ac.uns.ftn.xwsservice.dao.XPathDao;
import rs.ac.uns.ftn.xwsservice.service.XMLService;

import java.util.ArrayList;

@Service
public class XMLServiceImpl implements XMLService {

    UnmarshallDao unmarshallDao;
    XPathDao xPathDao;

    @Override
    public String retrieveObjectString(String fileName) {
        unmarshallDao = new UnmarshallDao();
        String result = null;

        try {
            result = unmarshallDao.retrieveUnmarshalledObjects(fileName);
        } catch (Exception e) {}

        return result;
    }

    @Override
    public ArrayList<String> retrieveXPath(String expr) {
        xPathDao = new XPathDao();
        ArrayList<String> results = null;
        try {
            results = xPathDao.retrieveXPathExpressionResults(expr);
        } catch (Exception e) {}

        return results;
    }

}
