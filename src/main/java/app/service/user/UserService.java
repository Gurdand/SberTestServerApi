package app.service.user;

public interface UserService {

    boolean authorization(String login);

    void registration(String login);

    boolean isLoginExist(String login);
}
