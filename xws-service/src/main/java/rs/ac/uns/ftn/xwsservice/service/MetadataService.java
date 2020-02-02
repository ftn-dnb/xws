package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.dto.request.SearchMetadataDTO;

import java.util.List;

public interface MetadataService {

    void metadataWrite(String filePath);
    List<String> metaDataSelect(SearchMetadataDTO dto);

}
