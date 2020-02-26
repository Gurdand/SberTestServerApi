package app.model;

public class Answer {

    private Long userId;
    private Long taskId;
    private String answer;
    private boolean isPassed;

    public Answer(Long userId, Long taskId, String answer, boolean isPassed) {
        this.userId = userId;
        this.taskId = taskId;
        this.answer = answer;
        this.isPassed = isPassed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
