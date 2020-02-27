package app.service.statistic;

import app.dao.StatisticRepository;
import app.model.Statistic;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    private StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Statistic getStatistic() {
        int taskCount = statisticRepository.taskCount();
        return new Statistic(
                statisticRepository.userCount(),
                statisticRepository.UserCountHaveAnswer(),
                statisticRepository.UserCountHaveAllAnswer(taskCount),
                statisticRepository.UserCountHaveAllAnswerIsPass(taskCount)
        );
    }
}
