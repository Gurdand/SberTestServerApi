package app.restcontroller;

import app.dto.AnswerDto;
import app.service.answer.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/answer")
public class AnswerRestController {

    private AnswerService answerService;

    public AnswerRestController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(path = "/{taskId}/{answer}")
    public ResponseEntity<?> checkAnswer(@PathVariable Integer taskId,
                                      @PathVariable String answer,
                                      HttpSession session) {

        String login = (String) session.getAttribute("Authorization");

        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<AnswerDto> answerDto = answerService.checkUserAnswer(login, taskId, answer);

        if (answerDto.isPresent()) {
            return new ResponseEntity<>(answerDto, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/{userLogin}")
    public ResponseEntity<?> getUserAnswer(@PathVariable(required = false) String userLogin,
                                           HttpSession session) {
        if (userLogin == null) {
            userLogin = (String)session.getAttribute("Authorization");
            if (userLogin.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(answerService.getUserAnswer(userLogin), HttpStatus.OK);

    }

}
