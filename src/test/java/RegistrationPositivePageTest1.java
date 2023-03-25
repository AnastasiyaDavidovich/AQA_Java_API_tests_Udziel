import entities.UserData;

import entities.UserRegisterResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.RegistrationPositivePage;

import static Config.Credentials.*;
import static io.restassured.RestAssured.given;

public class RegistrationPositivePageTest1 extends RegistrationPositivePage {

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
}
