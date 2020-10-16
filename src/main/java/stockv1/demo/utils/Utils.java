package stockv1.demo.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Marius Pop
 */
public class Utils {

    private static int workload = 12;

    public static String hashPassword(String passwordPlaintext) {
        String hashed_password = BCrypt.hashpw(passwordPlaintext, "$2a$12$oq9iIyva6xzOMpQW6AgqEe");

        return(hashed_password);
    }

    public static boolean checkPasswordMatch(String initialPassword, String finalPassword) {
        if (initialPassword.equals(finalPassword)) return true;
        else return false;
    }
}
