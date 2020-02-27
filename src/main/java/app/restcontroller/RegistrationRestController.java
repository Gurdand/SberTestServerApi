package app.restcontroller;

import app.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/registration")
public class RegistrationRestController {

    private final UserService userService;

    public RegistrationRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{login}", method = RequestMethod.POST)
    public ResponseEntity<String> registration(@PathVariable(value = "login") String login) {

        if (userService.isLoginExist(login)) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }

        userService.registration(login);
        return new ResponseEntity<>("User " + login + " registered", HttpStatus.CREATED);
    }
}
