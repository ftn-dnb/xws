package rs.ac.uns.ftn.xwsservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     *  Registracija novog korisnika
     */
    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody String xmlUserData) {
        // TODO: Implementirati!
        return ResponseEntity.ok().build();
    }
}
