package pageobject;

import entities.UserData;
import entities.UserRegisterResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import static Config.Config.BASE_URI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class RegistrationPositivePage extends BasePage {

    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
    }

    protected boolean deleteUser(String email, String password){
        String body = "{\n" +
                "    \"current_password\" : \"" + password + "\"\n" +
                "}";
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getUserToken(email, password))
                .body(body)
                .when()
                .delete("users/me/");
        return response.then().extract().statusCode() == 204;
    }

    public String getUserToken(String email, String password){
        String body = "{\n" +
                " \"email\": \"" + email + "\",\n" +
                " \"password\": \"" + password + "\"\n" +
                "}";
        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("jwt/create/");
        response.then().log().all().statusCode(200);
        return response.then().extract().response().jsonPath().getString("access");
    }


}
