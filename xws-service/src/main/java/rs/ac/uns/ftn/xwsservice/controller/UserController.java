package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.xwsservice.dto.UserRegistrationDTO;
import rs.ac.uns.ftn.xwsservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/add-user")
    public ResponseEntity addUser(@Valid @RequestBody UserRegistrationDTO userInfo) {
        userService.addUser(userInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public/verify-account/{token}")
    public ResponseEntity verifyUserAccount(@PathVariable String token) {
        userService.activateAccount(token);
        return ResponseEntity.ok().build();
    }
}
