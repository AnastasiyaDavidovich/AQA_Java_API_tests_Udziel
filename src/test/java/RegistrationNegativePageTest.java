import entities.UserData;

import entities.UserRegisterResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.RegistrationNegativePage;

import static Config.Credentials.*;
import static io.restassured.RestAssured.given;

public class RegistrationNegativePageTest extends RegistrationNegativePage {



    @Test (description = "with invalid e-mail: empty field")
    public void userRegistration_7(){

        UserRegisterResponse userDataResponse = userRegistration(PASSWORD, USERNAME, EMAIL_EMPTY);
        Assert.assertEquals(userDataResponse.getStatusCod(), 400);
        Assert.assertTrue(userDataResponse.getEmail().contains("Это поле не может быть пустым."));

    }

    @Test (description = "with invalid e-mail: 255 symbols ")
    public void userRegistration_8(){

        UserRegisterResponse userDataResponse = userRegistration(PASSWORD, USERNAME, EMAIL_255);
        Assert.assertEquals(userDataResponse.getStatusCod(), 400);
        Assert.assertTrue(userDataResponse.getEmail().contains("не более 254 символов"));
    }

    @Test (description = "with invalid e-mail: without @")
    public void userRegistration_9(){

        UserRegisterResponse userDataResponse = userRegistration(PASSWORD, USERNAME, EMAIL_WITHOUT_AT);
        Assert.assertEquals(userDataResponse.getStatusCod(), 400);
        Assert.assertTrue(userDataResponse.getEmail().contains("Введите правильный адрес"));
    }

    @Test (description = "with invalid e-mail: contains \"..\"")
    public void userRegistration_10(){

        UserRegisterResponse userDataResponse = userRegistration(PASSWORD, USERNAME, EMAIL_TWO_DOT);
        Assert.assertEquals(userDataResponse.getStatusCod(), 400);
        Assert.assertTrue(userDataResponse.getEmail().contains("Введите правильный адрес"));

    }

    @Test (description = "with invalid e-mail: starts with \".\"")
    public void userRegistration_11(){

        UserRegisterResponse userDataResponse = userRegistration(PASSWORD, USERNAME, EMAIL_START_DOT);
        Assert.assertEquals(userDataResponse.getStatusCod(), 400);
        Assert.assertTrue(userDataResponse.getEmail().contains("Введите правильный адрес"));
    }

    @Test (description = "with invalid e-mail: domain part starts with .")
    public void userRegistration_12(){

        UserRegisterResponse userDataResponse = userRegistration(PASSWORD, USERNAME, EMAIL_DOM_START_DOT);
        Assert.assertEquals(userDataResponse.getStatusCod(), 400);
        Assert.assertTrue(userDataResponse.getEmail().contains("Введите правильный адрес"));
    }

}
