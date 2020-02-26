package app.restcontroller;

import app.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api")
public class AuthorizationRestController {

    private final UserService userService;

    public AuthorizationRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/authorization/{login}", method = RequestMethod.GET)
    public ResponseEntity<String> authorization(@PathVariable(value = "login") String login,
                                                HttpSession session) {
        if (userService.authorization(login)) {
            session.setAttribute("Authorization", login);
            return new ResponseEntity<>("Authorized: " + login, HttpStatus.OK);
        }
        return new ResponseEntity<>("Authorization failed", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("Authorization");
        return new ResponseEntity<>("Logout", HttpStatus.OK);
    }
}
