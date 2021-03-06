package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private Long userId;
    private Long taskId;
    private String answer;
    private boolean isPassed;
}
