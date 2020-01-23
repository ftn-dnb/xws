package rs.ac.uns.ftn.xwsservice.mappers;

import rs.ac.uns.ftn.xwsservice.dto.response.BusinessProcessDTO;
import rs.ac.uns.ftn.xwsservice.model.PoslovniProces;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessProcessMapper {

    public static BusinessProcessDTO toDto(PoslovniProces process) {
        return new BusinessProcessDTO(process);
    }

    public static List<BusinessProcessDTO> toListDto(List<PoslovniProces> processes) {
        return processes.stream().map(process -> new BusinessProcessDTO(process)).collect(Collectors.toList());
    }

    private BusinessProcessMapper() {
    }
}
