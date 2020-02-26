package app.model;

import java.util.List;

public class Task {

    private Long id;

    private String question;

    private List<String> answerOptions;

    private String correctAnswer;

    public Task(Long id, String question, List<String> answerOptions, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answerOptions=" + answerOptions +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
