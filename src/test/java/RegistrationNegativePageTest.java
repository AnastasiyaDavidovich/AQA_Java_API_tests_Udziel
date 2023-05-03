import entities.UserData;

import entities.UserRegisterErrorResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.RegistrationNegativePage;

import static Config.Credentials.*;
import static Config.TestData.*;
import static Config.TestData.EMAIL;
import static Config.TestData.USERNAME;
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
            response.then().extract().statusCode(),
            response.then().extract().jsonPath().getString("password"),
            response.then().extract().jsonPath().getString("username"));
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

    @Test (description = "with invalid password: 7 symbols")
    public void userRegistration_21(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD_7_SYMBOLS, USERNAME, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getPassword().contains("Введённый пароль слишком короткий"));
    }

    @Test (description = "with invalid password: equal to name + \"1\"")
    public void userRegistration_22(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD_LIKE_NAME, USERNAME, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getPassword().contains("Введённый пароль слишком похож на username"));
    }

    @Test (description = "with invalid password: equal to email")
    public void userRegistration_23(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD_LIKE_EMAIL, USERNAME, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getPassword().contains("Введённый пароль слишком похож на email address"));
    }

    @Test (description = "with invalid password: numbers only >= 8 symbols")
    public void userRegistration_24(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD_NUM, USERNAME, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getPassword().contains("Введённый пароль состоит только из цифр"));
    }

    @Test (description = "with invalid password: \"qwerty\"")
    public void userRegistration_25(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD_QWERTY, USERNAME, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getPassword().contains("Введённый пароль слишком широко распространён"));
    }

    @Test (description = "with invalid password: empty field")
    public void userRegistration_26(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD_EMPTY, USERNAME, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getPassword().contains("Это поле не может быть пустым"));
    }

    @Test (description = "with invalid name: empty field")
    public void userRegistration_32(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME_EMPTY, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getName().contains("Это поле не может быть пустым"));
    }

    @Test (description = "with invalid name: 151 symbol")
    public void userRegistration_33(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME_151, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getName().contains("Убедитесь, что это значение содержит не более 150 символов"));
    }

    @Test (description = "with invalid name: contains *")
    public void userRegistration_34_1(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME_STAR, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getName().contains("Введите правильное имя пользователя"));
    }
    @Test (description = "with invalid name: contains /")
    public void userRegistration_34_2(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME_SLASH, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getName().contains("Введите правильное имя пользователя"));
    }

    @Test (description = "with invalid name: contains #")
    public void userRegistration_34_3(){
        UserRegisterErrorResponse userDataResponseError = userRegisterError(PASSWORD, USERNAME_HASH, EMAIL);
        Assert.assertEquals(userDataResponseError.getStatusCode(), 400);
        Assert.assertTrue(userDataResponseError.getName().contains("Введите правильное имя пользователя"));
    }


}
