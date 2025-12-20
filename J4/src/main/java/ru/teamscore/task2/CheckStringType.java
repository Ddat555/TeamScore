package ru.teamscore.task2;

public class CheckStringType {


    private static final String EMAIL_REGEX =
            "^\\s*[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\s*$";

    private static final String PHONE_REGEX =
            "^\\s*\\+7[-\\s]*(?:\\(\\d{3}\\)|\\d{3})[-\\s]*\\d{3}[-\\s]*\\d{2}[-\\s]*\\d{2}\\s*$";

    private static final String INN_REGEX =
            "^\\s*(?:\\d{10}|\\d{12})\\s*$";

    private static final String USERNAME_REGEX =
            "^\\s*[a-zA-Z][a-zA-Z0-9._$]{7,}\\s*$";

    public static StringType check(String text){
        if (text.matches(EMAIL_REGEX)) {
            return StringType.EMAIL;
        }
        if (text.matches(PHONE_REGEX)) {
            return StringType.PHONE;
        }
        if (text.matches(INN_REGEX)) {
            return StringType.INN;
        }
        if (text.matches(USERNAME_REGEX)) {
            return StringType.USERNAME;
        }
        return StringType.NONE;
    }
}
