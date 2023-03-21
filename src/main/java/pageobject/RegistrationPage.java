package pageobject;

import org.testng.annotations.BeforeTest;

import static Config.Config.BASE_URI;
import static io.restassured.RestAssured.baseURI;
public class RegistrationPage extends BasePage {
    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
    }


}
