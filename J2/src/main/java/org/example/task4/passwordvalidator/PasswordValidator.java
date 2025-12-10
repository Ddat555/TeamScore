package org.example.task4.passwordvalidator;

<<<<<<< Updated upstream
import org.example.task4.ValidationResult;

/** Проверка пароля на сложность.
=======
/**
 * Проверка пароля на сложность.
>>>>>>> Stashed changes
 * Пароль должен отвечать следующим требованиям:
 * - не менее 8 символов в длину
 * - содержит строчные, заглавные буквы и цифры
 * - не должен совпадать с имененем пользователя
 * - не должен содержать пробельных символов, табуляции и кавычек
 */
public class PasswordValidator {
    /**
     * Проверка валидности пароля
     * @param password пароль
     * @param userName имя пользователя
     * @return возвращает true, если пароль отвечает всем требованиям
     */
    public static boolean isValidPassword(String password, String userName) {
        return validatePassword(password,userName).isValid();
    }

    public static ValidationResult validatePassword(String password, String userName){
        ValidationResult validationResult = new ValidationResult();
        if (password.length() < 8) {
            validationResult.addExceptionMessage("Пароль не должен быть короче 8 символов");
        }
        if (!hasDigits(password)) {
            validationResult.addExceptionMessage("Пароль должен содержать цифры");
        }
        if (!hasLowercase(password)) {
            validationResult.addExceptionMessage("Пароль должен содержать строчные символы");
        }
        if (!hasUppercase(password)) {
            validationResult.addExceptionMessage("Пароль должен содержать заглавные символы");
        }
        if (password.equals(userName)) {
            validationResult.addExceptionMessage("Пароль не должен равняться имени пользователя");
        }
        if (hasSpacesOrQuotes(password)) {
            validationResult.addExceptionMessage("Пароль не должен содержать пробелы, отступы или \"");
        }
        validationResult.setValid(validationResult.getExceptionMessageList().isEmpty());
        return validationResult;
    }

    private static boolean hasDigits(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasLowercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isLowerCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasUppercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSpacesOrQuotes(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isSpaceChar(symbol)
                    || symbol == '\t' || symbol == '"') {
                return true;
            }
        }
        return false;
    }
}
