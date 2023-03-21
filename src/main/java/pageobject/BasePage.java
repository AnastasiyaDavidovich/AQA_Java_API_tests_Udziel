package pageobject;

import org.testng.annotations.*;
import static io.restassured.RestAssured.baseURI;
import static Config.Config.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BasePage {
    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
    }

}

