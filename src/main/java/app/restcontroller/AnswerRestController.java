package app.restcontroller;

import app.service.task.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/api/task/check")
public class AnswerRestController {

    private AnswerService answerService;

    public AnswerRestController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping(path = "/{taskId}/{answer}")
    public ResponseEntity<?> checkAnswer(@PathVariable Integer taskId,
                                      @PathVariable String answer,
                                      HttpSession session) {

        String login = (String) session.getAttribute("Login");

        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (answerService.checkUserAnswer(login, taskId, answer)) {
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>("Wrong answer", HttpStatus.CREATED);
        }

    }
}
