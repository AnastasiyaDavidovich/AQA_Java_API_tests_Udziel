package Config;

import com.github.javafaker.Faker;

public class TestData {
    private static final Faker faker = new Faker();

    public static final String EMAIL = faker.internet().emailAddress();
    public static final String USERNAME = "UserUser";

   //  Emails
    public static final String EMAIL_LOW = faker.internet().emailAddress().toLowerCase();
    public static final String EMAIL_UP_LOW = faker.internet().emailAddress().toUpperCase();
    public static final String EMAIL_NUM = faker.bothify("?????????#@example.com");
    public static final String EMAIL_DOT = faker.letterify("?????.???.test@example.com");
    public static final String EMAIL_DOT_DOM = faker.letterify("?????????.test@example.???.?.com");
    public static final String EMAIL_DASH = faker.letterify("?????-??????@example.com");

    public static final String EMAIL_EMPTY = "";
    public static final String EMAIL_255 = "1".repeat(243).concat("@example.com");
    public static final String EMAIL_WITHOUT_AT = "testtestexample.com";
    public static final String EMAIL_TWO_DOT = "test..test@example.com";
    public static final String EMAIL_START_DOT = ".testtest@example.com";
    public static final String EMAIL_DOM_START_DOT = "testtest@.example.com";
    public static final String EMAIL_POLISH_SYM = "testtestń@example.com";


    //    Usernames
    public static final String USERNAME_LOW = faker.letterify("??????????").toLowerCase();
    public static final String USERNAME_LOW_UP = "USERname";
    public static final String USERNAME_CYR = "юзерюзерюзер";
    public static final String USERNAME_NUM = faker.bothify("#??##?????");
    public static final String USERNAME_SPEC = "123sfdjh@+.-_";
    public static final String USERNAME_LIKE_EMAIL = EMAIL;

    public static final String USERNAME_EMPTY = "";
    public static final String USERNAME_151 = "1".repeat(151);
    public static final String USERNAME_HASH = "aa123456789#";
    public static final String USERNAME_STAR = "aa123456789*";
    public static final String USERNAME_SLASH = "aa123456789/";

    //    Passwords
    public static final String PASSWORD_LOW = faker.letterify("??????????").toLowerCase();
    public static final String PASSWORD_UPPER = faker.letterify("??????????").toUpperCase();
    public static final String PASSWORD_LOW_UP = "DFGHJklyui";
    public static final String PASSWORD_LET_NUM = faker.bothify("#??##???????");
    public static final String PASSWORD_SPEC = "~!?@#%^&*_+()[]{}<>/|'.,;:";

    public static final String PASSWORD_7_SYMBOLS = "sdfghjk";
    public static final String PASSWORD_LIKE_NAME = USERNAME + "1";
    public static final String PASSWORD_LIKE_EMAIL = EMAIL;
    public static final String PASSWORD_NUM = faker.numerify("123456789");
    public static final String PASSWORD_QWERTY = "qwerty";
    public static final String PASSWORD_EMPTY = "";


    public static final String USER_RESPONSE39 = "слишком короткий";
    public static final String USER_RESPONSE40 = "слишком похож на username";
    public static final String USER_RESPONSE41 = "слишком похож на email";
    public static final String USER_RESPONSE42 = "только из цифр";
    public static final String USER_RESPONSE43 = "широко распространён";
    public static final String USER_RESPONSE49 = "не может быть пустым";
    public static final String USER_RESPONSE50 = "не более 150 символов";
    public static final String USER_RESPONSE51 = "может содержать только";
    public static final String USER_RESPONSE64 = "не может быть пустым";
    public static final String USER_RESPONSE65 = "не более 254 символов";
    public static final String USER_RESPONSE66 = "Введите правильный адрес электронной почты";
    public static final String USER_RESPONSE67 = "Введите правильный адрес электронной почты";
    public static final String USER_RESPONSE68 = "Введите правильный адрес электронной почты";
    public static final String USER_RESPONSE69 = "Введите правильный адрес электронной почты";
    public static final String USER_RESPONSE70 = "может содержать только";
}
