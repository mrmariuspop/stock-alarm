package stockv1.demo.service;

import org.springframework.stereotype.Service;
import stockv1.demo.model.User;
import stockv1.demo.repository.UserRepository;
import stockv1.demo.utils.Utils;

import static stockv1.demo.utils.Constants.*;

/**
 * @author Marius Pop
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(String firstname, String lastname, String email, String password) throws Exception {
        if (userRepository.findByEmail(email) != null) {
            throw new Exception(EXISTENT_USER);
        }
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(Utils.hashPassword(password));
        userRepository.save(user);
    }

    public String login(String email, String password) throws Exception {
        //find user by email
        User userByEmail = userRepository.findByEmail(email);

        if (userByEmail == null) {
            throw new Exception(USER_NOT_FOUND);
        } else {
            boolean match = Utils.checkPasswordMatch(userByEmail.getPassword(), Utils.hashPassword(password));
            System.out.println(match);
            if (!match) throw new Exception(INCORRECT_PASSWORD);
            else return LOGIN_SUCCESSFUL;
        }
    }
}
