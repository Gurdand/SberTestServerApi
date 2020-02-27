package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistic {

    private int userCount;
    private int userInProgress;
    private int usersCompleteTest;
    private int usersCompleteTestPerfect;
}
