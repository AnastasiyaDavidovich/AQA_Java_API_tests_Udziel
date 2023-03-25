
import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageobject.BasePage;
//import org.testng.annotations.*;


import static Config.Config.BASE_URI;
import static Config.Credentials.ADMIN_EMAIL;
import static Config.Credentials.ADMIN_PASSWORD;
import static io.restassured.RestAssured.baseURI;
import static Config.Credentials.*;
//import static Config.Config.*;
import static io.restassured.RestAssured.given;

public class BasePageTest extends BasePage {

    private String accessToken;

    @BeforeTest
    public void start() {
        baseURI = BASE_URI;

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


}
