package rs.ac.uns.ftn.xwsservice.service;


import rs.ac.uns.ftn.xwsservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
}
