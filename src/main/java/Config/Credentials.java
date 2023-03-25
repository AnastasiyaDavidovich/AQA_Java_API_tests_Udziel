package Config;

import com.github.javafaker.Faker;

public class Credentials {
    private static final Faker faker = new Faker();
    public static final String EMAIL = "testtest112@example.com";
    public static final String EMAIL_LOW = faker.internet().emailAddress().toLowerCase();
    public static final String EMAIL_UP_LOW = faker.internet().emailAddress().toUpperCase();
    public static final String EMAIL_NUM = faker.bothify("#?????????@example.com");
    public static final String EMAIL_DOT = faker.letterify("?????.???.test@example.com");
    public static final String EMAIL_DOT_DOM = faker.letterify("?????????.test@example.???.?.com");
    public static final String EMAIL_DASH = faker.letterify("?????-??????@example.com");
//    public static final String EMAIL_NUM = "3testtest@example.com";
//    public static final String EMAIL_DOT = "test.test.test@example.com";
//    public static final String EMAIL_DOT_DOM = "testtest@example.com.v.com";
//    public static final String EMAIL_DASH = "test-test@example.com";
    public static final String EMAIL_EMPTY = "";
    public static final String EMAIL_255 = "1".repeat(243).concat("@example.com");
    public static final String EMAIL_WITHOUT_AT = "testtestexample.com";
    public static final String EMAIL_TWO_DOT = "test..test@example.com";
    public static final String EMAIL_START_DOT = ".testtest@example.com";
    public static final String EMAIL_DOM_START_DOT = "testtest@.example.com";

    public static final String PASSWORD = "QWEr123456";
    public static final String USERNAME = "UserUser";

    public static final String ADMIN_EMAIL = "elena.kuzheleva.exlab@gmail.com";
    public static final String  ADMIN_PASSWORD = "qwertyQ1_";


}
