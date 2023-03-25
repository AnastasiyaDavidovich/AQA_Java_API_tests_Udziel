package pageobject;

import entities.UserData;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import static Config.Config.BASE_URI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RegistrationNegativePage extends BasePage {

    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
    }




}
