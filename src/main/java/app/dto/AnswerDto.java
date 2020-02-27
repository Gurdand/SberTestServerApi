package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDto {

    private Long taskId;
    private String answer;
    private boolean isPassed;
}
