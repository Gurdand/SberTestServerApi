package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Task {

    private Long id;

    private String question;

    private List<String> answerOptions;

    private String correctAnswer;
}
