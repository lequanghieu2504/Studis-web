package utils.validatorUtils;

import model.User;

public class DataFormatValidator {

    public static boolean isIdValid(int id) {
        return id > 0;
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isNameValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return (name.length() >= 3) && (name.length() <= 300);
    }

    public static boolean isNameOrEmailValid(String nameOrEmail) {
        if (nameOrEmail == null || nameOrEmail.isEmpty()) {
            return false;
        }
        if (nameOrEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return true;
        }
        return (nameOrEmail.length() >= 3) && (nameOrEmail.length() <= 300);
    }

    public static boolean isPasswordValid(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    }

    public static boolean isUserValid(User user) {
        if (user == null) {
            return false;
        }
        return isNameValid(user.getName()) && isEmailValid(user.getEmail());
    }
}
