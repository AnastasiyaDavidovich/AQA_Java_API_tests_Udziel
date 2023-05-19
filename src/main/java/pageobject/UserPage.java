package pageobject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import static Config.Config.BASE_URI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static Config.Credentials.*;

public class UserPage extends BasePage {
    private String accessToken;

    @BeforeTest
    public void start() {
        baseURI = BASE_URI;
        userRegistration(PASSWORD, USERNAME, EMAIL);
        String body = "{\n" +
                " \"email\": \"" + EMAIL + "\",\n" +
                " \"password\": \"" + PASSWORD + "\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("jwt/create/");

        response.then().log().all().statusCode(200);
        setAccessToken(response.then().extract().response().jsonPath().getString("access"));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }


    public Response changeUsername(String newName, String accessToken){
        return given()
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + accessToken)
                .body("{\n" +
                        " \"username\": \"" + newName + "\"\n" +
                        "}")
                .patch("users/me/");
    }

    public Response changeUserPassword(String newPassword, String accessToken){
        return given()
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + accessToken)
                .body("{\n" +
                        " \"new_password\": \"" + newPassword + "\"\n" +
                        "}")
                .post("users/set_password/");
    }
    public Response changeEmail(String newEmail, String accessToken){
        return given()
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + accessToken)
                .body("{\n" +
                        " \"email\": \"" + newEmail + "\"\n" +
                        "}")
                .patch("users/me/");
    }

    public Response userLogin(String email, String password){
        String body = "{\n" +
                " \"email\": \"" + email + "\",\n" +
                " \"password\": \"" + password + "\"\n" +
                "}";
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("jwt/create/");
    }

    public Response userChange(String newEmail, String newName, String accessToken){
        return given()
                .when()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + accessToken)
                .body("{\n" +
                        " \"email\": \"" + newEmail + "\"\n" +
                        " \"username\": \"" + newName + "\"\n" +
                        "}")
                .put("users/me/");
    }


}
