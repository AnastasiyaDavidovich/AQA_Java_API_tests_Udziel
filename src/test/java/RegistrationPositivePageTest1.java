import entities.UserData;

import entities.UserRegisterResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pageobject.RegistrationPositivePage;

import static Config.Credentials.*;
import static Config.TestData.*;
import static Config.TestData.EMAIL;
import static Config.TestData.USERNAME;
import static io.restassured.RestAssured.given;

public class RegistrationPositivePageTest1 extends RegistrationPositivePage {

    private void registrationOk(String password, String username, String email){
        UserRegisterResponse userDataRegistration = userRegistration(password, username, email);
        Assert.assertEquals(userDataRegistration.getStatusCode(), 201);
        String expEmail = email.toLowerCase();
        Assert.assertEquals(userDataRegistration.getEmail(), expEmail);
        String token = getUserToken(email, password);
        Assert.assertNotNull(token);
        boolean response = deleteUser(email, password);
        Assert.assertTrue(response);
    }

    private void registrationUsernameOk(String password, String username, String email){
        UserRegisterResponse userDataRegistration = userRegistration(password, username, email);
        Assert.assertEquals(userDataRegistration.getStatusCode(), 201);
        String expUsername = username.toLowerCase();
        Assert.assertEquals(userDataRegistration.getUsername(), expUsername);
        String token = getUserToken(email, password);
        Assert.assertNotNull(token);
        boolean response = deleteUser(email, password);
        Assert.assertTrue(response);
    }
    private void registrationPasswordOk(String password, String username, String email){
        UserRegisterResponse userDataRegistration = userRegistration(password, username, email);
        Assert.assertEquals(userDataRegistration.getStatusCode(), 201);
//        String expPassword = password.toLowerCase();
//        Assert.assertEquals(userDataRegistration.getPassword(), password);
        String token = getUserToken(email, password);
        Assert.assertNotNull(token);
        boolean response = deleteUser(email, password);
        Assert.assertTrue(response);
    }

//    @AfterTest
//    public void end(String email, String password){
//        String token = getUserToken(email, password);
//        Assert.assertNotNull(token);
//        boolean response = deleteUser(email, password);
//        Assert.assertTrue(response);
//    }

    @Test (description = "with valid e-mail: lower case")
    public void userRegistration_1(){
        registrationOk(PASSWORD, USERNAME, EMAIL_LOW);
    }

    @Test (description = "with valid e-mail: lower case and upper case")
    public void userRegistration_2(){
        registrationOk(PASSWORD, USERNAME, EMAIL_UP_LOW);
    }

    @Test (description = "with valid e-mail: starting with number")
    public void userRegistration_3(){
        registrationOk(PASSWORD, USERNAME,EMAIL_NUM);
    }

    @Test (description = "with valid e-mail: with several dots in name part, not in a row")
    public void userRegistration_4(){
        registrationOk(PASSWORD, USERNAME,EMAIL_DOT);
    }

    @Test (description = "with valid e-mail: with several dots in domain part, not in a row")
    public void userRegistration_5(){
        registrationOk(PASSWORD, USERNAME,EMAIL_DOT_DOM);
    }

    @Test (description = "with valid e-mail: with \"-\" in name part")
    public void userRegistration_6(){
        registrationOk(PASSWORD, USERNAME,EMAIL_DASH);
    }

    @Test (description = "with valid password: lowerCase letters only >=8 symbols")
    public void userRegistration_16() {
        registrationPasswordOk(PASSWORD_LOW, USERNAME,EMAIL);
    }

    @Test (description = "with valid password: UpperCase letters only >=8 symbols")
    public void userRegistration_17() {
        registrationPasswordOk(PASSWORD_UPPER, USERNAME,EMAIL);
    }

    @Test (description = "with valid password: lowerCase and UpperCase letters only >=8 symbols")
    public void userRegistration_18() {
        registrationPasswordOk(PASSWORD_LOW_UP, USERNAME,EMAIL);
    }

    @Test (description = "with valid password: letters and numbers >=8 symbols")
    public void userRegistration_19() {
        registrationPasswordOk(PASSWORD_LET_NUM, USERNAME,EMAIL);
    }

    @Test (description = "with valid password: special symbols >= 8: ~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / | ' . , : ;")
    public void userRegistration_20() {
        registrationPasswordOk(PASSWORD_SPEC, USERNAME,EMAIL);
    }

    @Test (description = "with valid name: Uppercase and Lowercase letters")
    public void userRegistration_27() {
        registrationUsernameOk(PASSWORD, USERNAME_LOW, EMAIL);
    }

    @Test (description = "with valid name: Cyrillic letters")
    public void userRegistration_28() {
        registrationUsernameOk(PASSWORD, USERNAME_CYR, EMAIL);
    }

    @Test (description = "with valid name: Contains numbers")
    public void userRegistration_29() {
        registrationUsernameOk(PASSWORD, USERNAME_NUM, EMAIL);
    }

    @Test (description = "with valid name: Contains \"@+.-_\"")
    public void userRegistration_30() {
        registrationUsernameOk(PASSWORD, USERNAME_SPEC, EMAIL);
    }

    @Test (description = "with valid name: Equal to email")
    public void userRegistration_31() {
        registrationUsernameOk(PASSWORD, USERNAME_LIKE_EMAIL, EMAIL);
    }



}
