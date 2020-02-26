package app.dao.impl;

import app.dao.UserRepository;
import app.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<User> findUserByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE login = ?",
                    new Object[]{login},
                    (rs, rowNum) -> Optional.of(new User(
                            rs.getLong("id"),
                            rs.getString("login"))));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void creatUser(User user) {
        jdbcTemplate.update("INSERT INTO users (login) VALUES (?)", user.getLogin());

    }
}
