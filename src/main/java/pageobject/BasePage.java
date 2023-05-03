package pageobject;

import entities.UserData;
import entities.UserDataToDelete;
import entities.UserDataToLogin;
import entities.UserRegisterResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

import static Config.Credentials.*;
import static Config.Credentials.PASSWORD;
import static io.restassured.RestAssured.baseURI;
import static Config.Config.*;
import static io.restassured.RestAssured.*;

public class BasePage {

    private String accessToken;

    @BeforeTest
    public void start() {
        baseURI = BASE_URI;

  }
    protected UserRegisterResponse userRegistration(String password, String username, String email){
        UserData userRegistration = new UserData(password, username, email);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        UserRegisterResponse userResponse = new UserRegisterResponse(response.then().extract().jsonPath().getInt("id"),
                response.then().extract().jsonPath().getString("username"),
                response.then().extract().jsonPath().getString("email"),
                response.then().extract().statusCode(),
                response.then().extract().jsonPath().getString("password"));
        return userResponse;
    }

    public Response registerUser(UserData userRegistration) {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");
        System.out.println("USER DATA TO REGISTRATION: " + userRegistration.getEmail() + " password: " + userRegistration.getPassword() + " NAME: " + userRegistration.getUsername());
        return response;
    }

    public String getAccessToken(UserDataToLogin userToLogin) {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userToLogin)
                .post("jwt/create/");

        return response.then().extract().response().jsonPath().getString("access");
    }

    public Response deleteUserMe(UserDataToDelete userToDelete){
        UserDataToLogin userToLogin = new UserDataToLogin(userToDelete.getEmail().toLowerCase(),userToDelete.getPassword());
        String body = "{\n" +
                " \"current_password\": \"" + userToDelete.getPassword() + "\"\n" +
                "}";
        return given()
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getAccessToken(userToLogin))
                .body(body)
                .delete("users/me/");
    }

}

