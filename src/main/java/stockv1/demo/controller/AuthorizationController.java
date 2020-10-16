package stockv1.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stockv1.demo.service.UserService;

import static stockv1.demo.utils.Constants.*;

/**
 * @author Marius Pop
 */
@RestController
public class AuthorizationController {

    private final UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("register")
    @PostMapping("/register")
    public void register(@RequestParam(name = "firstname") String firstname,
                         @RequestParam(name = "lastname") String lastname,
                         @RequestParam(name = "email") String email,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "confirm password", required = true) String confirmPassword) throws Exception {
        if (password.equals(confirmPassword)) userService.save(firstname, lastname, email, password);
        else throw new Exception(NO_MATCH_PASSWORDS);
    }

    @ApiOperation("login")
    @PostMapping("/login")
    public String login(@RequestParam(name = "email") String email,
                      @RequestParam(name = "password") String password) throws Exception {
        return userService.login(email, password);
    }
}
