package app.dao;

import app.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByLogin(String login);

    void creatUser(User user);

}
