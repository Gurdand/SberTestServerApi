package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String question;
    private List<String> answerOptions;
}
