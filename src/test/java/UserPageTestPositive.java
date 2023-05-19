import com.github.javafaker.Faker;
import entities.UserData;
import entities.UserDataToDelete;
import entities.UserDataToLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.BasePage;
import pageobject.UserPage;

import static Config.Config.BASE_URI;

import static Config.Config.BASE_URI;
import static Config.Credentials.*;
import static Config.TestData.*;
import static Config.TestData.EMAIL;
import static Config.TestData.USERNAME;
import static io.restassured.RestAssured.*;

public class UserPageTestPositive extends BasePageTest {

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

    @AfterMethod
    public void deleteUserAfterTest() {
        basePage.deleteUserMe(userToDelete);
    }


    @Test (description = "get user's info")
    public void getInfoMe_13(){
        Response response = given()
                .header("Authorization", "Token " + getAccessToken())
                .when()
                .get("users/me/");
        response.then().log().all().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().getString("username"), USERNAME );
    }

    @Test (description = "delete user")
    public void deleteUser_14(){
        String body = "{\n" +
                "    \"current_password\" : \"" + PASSWORD + "\"\n" +
                "}";
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getAccessToken())
                .body(body)
                .when()
                .delete("users/me/");
        response.then().log().all().statusCode(204);
    }

    private void changePassword(String password, String accessToken) {
        Response response1 = userPage.changeUserPassword(password, accessToken);
        response1.then().log().all().statusCode(204);
        userToLogin.setPassword(password);
        userToDelete.setPassword(password);
        Assert.assertTrue(basePage.getAccessToken(userToLogin).length() > 10);
    }

    private void changeName(String username, String accessToken){
        Response response1 = userPage.changeUsername(username, accessToken);
        response1.then().log().all().statusCode(200);
        Assert.assertEquals(username, response1.then().extract().jsonPath().getString("username"));
    }

    private void changeEmail(String email, String accessToken) {
        Response response1 = userPage.changeEmail(email, accessToken);
        response1.then().log().all().statusCode(204);
        Assert.assertEquals(email, response1.then().extract().jsonPath().getString("email"));
    }

    @Test (description = "change password to valid: lowerCase letters only >=8 symbols")
    public void changePassword_34() {
        changePassword(PASSWORD_LOW, accessToken);
    }

    @Test (description = "change password to valid: UpperCase letters only >=8 symbols")
    public void changePassword_35() {
        changePassword(PASSWORD_UPPER, accessToken);
    }

    @Test (description = "change password to valid: lowerCase and UpperCase letters only >=8 symbols")
    public void changePassword_36() {
        changePassword(PASSWORD_LOW_UP, accessToken);
    }

    @Test (description = "change password to valid: letters and numbers >=8 symbols")
    public void changePassword_37() {
        changePassword(PASSWORD_LET_NUM, accessToken);
    }

    @Test (description = "change password to valid: special symbols >= 8: ~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / \\ | \" ' . , : ;")
    public void changePassword_38() {
        changePassword(PASSWORD_SPEC, accessToken);
    }

    @Test (description = "change name to valid: Uppercase and Lowercase letters")
    public void changeName_44() {
        changeName(USERNAME_LOW_UP, accessToken);
    }

    @Test (description = "change name to valid: Cyrillic letters")
    public void changeName_45() {
        changeName(USERNAME_CYR, accessToken);
    }

    @Test (description = "change name to valid: Contains numbers")
    public void changeName_46() {
        changeName(USERNAME_NUM, accessToken);
    }

    @Test (description = "change name to valid: Contains \"@+.-_\"")
    public void changeName_47() {
        changeName(USERNAME_SPEC, accessToken);
    }

    @Test (description = "change name to valid: Equal to email")
    public void changeName_48() {
        changeName(userToTest.getEmail(), accessToken);
    }

    @Test (description = "сhange: valid email + valid name")
    public void userChange_52(){
        Response response1 = userPage.userChange(EMAIL_UP_LOW, USERNAME_LOW_UP, accessToken);
        response1.then().log().all().statusCode(204);
        Assert.assertEquals(EMAIL_UP_LOW, response1.then().extract().jsonPath().getString("email"));
        Assert.assertEquals(USERNAME_LOW_UP, response1.then().extract().jsonPath().getString("username"));
    }

    @Test (description = "change email to valid: lower case")
    public void changeEmail_58() {
        changeEmail(EMAIL_LOW, accessToken);
    }
    @Test (description = "change email to valid: lower case and upper case")
    public void changeEmail_59() {
        changeEmail(EMAIL_UP_LOW, accessToken);
    }

    @Test (description = "change email to valid: starting with number")
    public void changeEmail_60() {
        changeEmail(EMAIL_NUM, accessToken);
    }

    @Test (description = "change email to valid: with several dots in name part, not in a row")
    public void changeEmail_61() {
        changeEmail(EMAIL_DOT, accessToken);
    }

    @Test (description = "change email to valid: with several dots in domain part, not in a row")
    public void changeEmail_62() {
        changeEmail(EMAIL_DOT_DOM, accessToken);
    }

    @Test (description = "change email to valid: with \"-\" in name part")
    public void changeEmail_63() {
        changeEmail(EMAIL_DASH, accessToken);
    }

    @Test (description = "User login with valid login  valid password")
    public void userLogin_71() {
        Response response1 = userPage.userLogin(EMAIL, PASSWORD_LOW);
        response1.then().statusCode(200);
    }

}
