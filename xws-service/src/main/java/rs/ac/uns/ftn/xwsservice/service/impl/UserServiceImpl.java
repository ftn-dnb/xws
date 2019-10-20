package rs.ac.uns.ftn.xwsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.xwsservice.dto.UserDTO;
import rs.ac.uns.ftn.xwsservice.exception.ApiRequestException;
import rs.ac.uns.ftn.xwsservice.model.User;
import rs.ac.uns.ftn.xwsservice.repository.UserRepository;
import rs.ac.uns.ftn.xwsservice.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findById(Long id) throws ApiRequestException {
        try {
            User user = userRepository.findById(id).get();
            return new UserDTO(user);
        } catch (NoSuchElementException e) {
            throw new ApiRequestException("User with id '" + id + "' doesn't exist.");
        }
    }

    @Override
    public UserDTO findByUsername(String username) throws ApiRequestException {
        try {
            User user = userRepository.findByUsername(username);
            return new UserDTO(user);
        } catch (UsernameNotFoundException e) {
            throw new ApiRequestException("User with username '" + username + "' doesn't exist.");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user)).collect(Collectors.toList());
    }
}
