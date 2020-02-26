package app.service.user;

import app.dao.UserRepository;
import app.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authorization(String login) {
        return isLoginExist(login);
    }

    @Override
    public void registration(String login) {
        userRepository.creatUser(new User(login));
    }

    @Override
    public boolean isLoginExist(String login) {
        return userRepository.findUserByLogin(login).isPresent();
    }


}
