package app.dao;

public interface StatisticRepository {

    Integer userCount();

    Integer taskCount();

    Integer UserCountHaveAnswer();

    Integer UserCountHaveAllAnswer(int taskCount);

    Integer UserCountHaveAllAnswerIsPass(int taskCount);

}
