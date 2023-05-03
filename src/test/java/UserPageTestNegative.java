import com.github.javafaker.Faker;
import entities.UserData;
import entities.UserDataToDelete;
import entities.UserDataToLogin;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.BasePage;
import pageobject.UserPage;

import static Config.Config.BASE_URI;
import static Config.Credentials.*;
import static Config.TestData.*;
import static Config.TestData.USERNAME;
import static io.restassured.RestAssured.baseURI;



public class UserPageTestNegative extends BasePageTest{
    protected UserData userToTest;
    protected UserDataToLogin userToLogin;
    protected UserDataToDelete userToDelete;
    protected String accessToken;
    protected static Faker faker = new Faker();
    protected static String userEmailRandom;
    protected static String userPasswordRandom;
    protected  UserPage userPage;
    protected BasePage basePage;


    @BeforeTest
    public void beforeAllTests() {
        baseURI = BASE_URI;
        basePage = new BasePage();
        userPage = new UserPage();
    }

    @BeforeMethod
    public void beforeEachTest(){
        userEmailRandom = faker.bothify("lena######????@mail.ru");
        userPasswordRandom = faker.bothify("###???###???Q_");
        userToTest = new UserData(userEmailRandom, userPasswordRandom, USERNAME);
        basePage.registerUser(userToTest);
        userToLogin = new UserDataToLogin(userToTest.getEmail(), userToTest.getPassword());
        accessToken = basePage.getAccessToken(userToLogin);
        userToDelete = new UserDataToDelete(userToTest.getEmail(), userToTest.getPassword());
    }

    private void changePasswordInvalid(String password, String accessToken, String responseText){
        Response response1 = userPage.changeUserPassword(password, accessToken);
        response1.then().log().all().statusCode(400);
        Assert.assertTrue(response1.then().extract().jsonPath().getString("new_password").contains(responseText));
    }

    private void changeNaneInvalid(String username, String accessToken, String responseText){
        Response response1 = userPage.changeUsername(username, accessToken);
        response1.then().log().all().statusCode(400);
        Assert.assertTrue(response1.then().extract().jsonPath().getString("username").contains(responseText));
    }

    private void changeEmailInvalid(String email, String accessToken, String responseText){
        Response response1 = userPage.changeEmail(email, accessToken);
        response1.then().log().all().statusCode(400);
        Assert.assertTrue(response1.then().extract().jsonPath().getString("email").contains(responseText));
    }

    @Test (description = "change password to invalid: 7 symbols")
    public void changePasswordInvalid_39() {
        changePasswordInvalid(PASSWORD_7_SYMBOLS, accessToken, USER_RESPONSE39);
    }

    @Test (description = "change password to invalid: equal to name + \"1\"")
    public void changePasswordInvalid_40() {
        changePasswordInvalid(PASSWORD_LIKE_NAME, accessToken, USER_RESPONSE40);
    }

    @Test (description = "change password to invalid: equal to email")
    public void changePasswordInvalid_41() {
        changePasswordInvalid(userToTest.getEmail(), accessToken, USER_RESPONSE41);
    }

    @Test (description = "change password to invalid: numbers only >= 8 symbols")
    public void changePasswordInvalid_42() {
        changePasswordInvalid(PASSWORD_NUM, accessToken, USER_RESPONSE42);
    }

    @Test (description = "change password to invalid: \"qwerty\"")
    public void changePasswordInvalid_43() {
        changePasswordInvalid(PASSWORD_QWERTY, accessToken, USER_RESPONSE43);
    }

    @Test (description = "change name to invalid: empty field")
    public void changeNaneInvalid_49() {
        changeNaneInvalid(USERNAME_EMPTY, accessToken, USER_RESPONSE49);
    }

    @Test (description = "change name to invalid: 151 symbol")
    public void changeNaneInvalid_50() {
        changeNaneInvalid(USERNAME_151, accessToken, USER_RESPONSE50);
    }

    @Test (description = "change name to invalid: contains \"#\"")
    public void changeNaneInvalid_51() {
        changeNaneInvalid(USERNAME_HASH, accessToken, USER_RESPONSE51);
    }

    @Test (description = "change name to invalid: contains \"*\"")
    public void changeNaneInvalid_51_1() {
        changeNaneInvalid(USERNAME_STAR, accessToken, USER_RESPONSE51);
    }
    @Test (description = "change name to invalid: contains \"/\"")
    public void changeNaneInvalid_51_2() {
        changeNaneInvalid(USERNAME_SLASH, accessToken, USER_RESPONSE51);
    }

    @Test (description = "change email to invalid: empty field")
    public void changeEmailInvalid_64() {
        changeEmailInvalid(EMAIL_EMPTY, accessToken, USER_RESPONSE64);
    }

    @Test (description = "change email to invalid: 255 symbols ")
    public void changeEmailInvalid_65() {
        changeEmailInvalid(EMAIL_255, accessToken, USER_RESPONSE65);
    }
    @Test (description = "change email to invalid: without @")
    public void changeEmailInvalid_66() {
        changeEmailInvalid(EMAIL_WITHOUT_AT, accessToken, USER_RESPONSE66);
    }
    @Test (description = "change email to invalid: contains \"..\"")
    public void changeEmailInvalid_67() {
        changeEmailInvalid(EMAIL_TWO_DOT, accessToken, USER_RESPONSE67);
    }
    @Test (description = "change email to invalid: starts with \".\"")
    public void changeEmailInvalid_68() {
        changeEmailInvalid(EMAIL_START_DOT, accessToken, USER_RESPONSE68);
    }
    @Test (description = "change email to invalid: domain part starts with \".\"")
    public void changeEmailInvalid_69() {
        changeEmailInvalid(EMAIL_DOM_START_DOT, accessToken, USER_RESPONSE69);
    }
    @Test (description = "change email to invalid: contents polish symbol \"ń\"")
    public void changeEmailInvalid_70() {
        changeEmailInvalid(EMAIL_POLISH_SYM, accessToken, USER_RESPONSE70);
    }
}
