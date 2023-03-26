import entities.UserData;

import entities.UserRegisterErrorResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.RegistrationNegativePage;

import static Config.Credentials.*;
import static io.restassured.RestAssured.given;

public class RegistrationNegativePageTest extends RegistrationNegativePage {

private UserRegisterErrorResponse userRegisterError(String password, String username, String email){
    UserData userRegistration = new UserData(password, username, email);

    Response response = given()
            .header("Content-Type", "application/json")
            .body(userRegistration)
            .post("users/");
    UserRegisterErrorResponse userResponseError = new UserRegisterErrorResponse(
            response.then().extract().jsonPath().getString("email"),
            response.then().extract().statusCode());
    return userResponseError;

}

    @Test (description = "with invalid e-mail: empty field")
    public void userRegistration_7(){

        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME, EMAIL_EMPTY);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getEmail().contains("Это поле не может быть пустым."));

    }

    @Test (description = "with invalid e-mail: 255 symbols ")
    public void userRegistration_8(){

        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME, EMAIL_255);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getEmail().contains("не более 254 символов"));
    }

    @Test (description = "with invalid e-mail: without @")
    public void userRegistration_9(){

        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME, EMAIL_WITHOUT_AT);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getEmail().contains("Введите правильный адрес"));
    }

    @Test (description = "with invalid e-mail: contains \"..\"")
    public void userRegistration_10(){

        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME, EMAIL_TWO_DOT);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getEmail().contains("Введите правильный адрес"));

    }

    @Test (description = "with invalid e-mail: starts with \".\"")
    public void userRegistration_11(){

        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME, EMAIL_START_DOT);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getEmail().contains("Введите правильный адрес"));
    }

    @Test (description = "with invalid e-mail: domain part starts with .")
    public void userRegistration_12(){

        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME, EMAIL_DOM_START_DOT);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getEmail().contains("Введите правильный адрес"));
    }

}
