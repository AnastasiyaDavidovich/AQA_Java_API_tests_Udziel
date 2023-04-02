package pageobject;

import entities.UserData;
import entities.UserRegisterResponse;
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
}

