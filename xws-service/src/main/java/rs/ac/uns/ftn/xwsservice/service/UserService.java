package rs.ac.uns.ftn.xwsservice.service;

import rs.ac.uns.ftn.xwsservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.xwsservice.model.User;

public interface UserService {

    User addUser(UserRegistrationDTO userInfo);
    boolean activateAccount(String token);
}
