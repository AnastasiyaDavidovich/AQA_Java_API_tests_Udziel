import entities.UserData;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Config.Credentials.*;
import static io.restassured.RestAssured.given;

public class RegistrationPageTest extends BasePageTest {

    @Test (description = "with valid e-mail: lower case")
    public void userRegistration_1(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME, EMAIL_LOW);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(201);
        Assert.assertEquals(USERNAME, response.then().extract().jsonPath().getString("username"));
    }

    @Test (description = "with valid e-mail: lower case and upper case")
    public void userRegistration_2(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_UP_LOW);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(201);
        Assert.assertEquals(USERNAME, response.then().extract().jsonPath().getString("username"));

    }

    @Test (description = "with valid e-mail: starting with number")
    public void userRegistration_3(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_NUM);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(201);
        Assert.assertEquals(USERNAME, response.then().extract().jsonPath().getString("username"));

    }

    @Test (description = "with valid e-mail: with several dots in name part, not in a row")
    public void userRegistration_4(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_DOT);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(201);
        Assert.assertEquals(USERNAME, response.then().extract().jsonPath().getString("username"),
                "TEST FAILED: Name of registered user not equal to user registration data");

    }

    @Test (description = "with valid e-mail: with several dots in domain part, not in a row")
    public void userRegistration_5(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_DOT_DOM);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(201);
        Assert.assertEquals(USERNAME, response.then().extract().jsonPath().getString("username"));

    }

    @Test (description = "with valid e-mail: with \"-\" in name part")
    public void userRegistration_6(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_DASH);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(201);
        Assert.assertEquals(USERNAME, response.then().extract().jsonPath().getString("username"));

    }

    @Test (description = "with invalid e-mail: empty field")
    public void userRegistration_7(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_EMPTY);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(400);
        Assert.assertTrue(response.then().extract().jsonPath().getString("email").contains("не может быть пустым"));

    }

    @Test (description = "with invalid e-mail: 255 symbols ")
    public void userRegistration_8(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_255);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(400);
        Assert.assertTrue(response.then().extract().jsonPath().getString("email").contains("не более 254 символов"));

    }

    @Test (description = "with invalid e-mail: without @")
    public void userRegistration_9(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_WITHOUT_AT);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(400);
        Assert.assertTrue(response.then().extract().jsonPath().getString("email").contains("Введите правильный адрес"));
    }

    @Test (description = "with invalid e-mail: contains \"..\"")
    public void userRegistration_10(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_TWO_DOT);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(400);
        Assert.assertTrue(response.then().extract().jsonPath().getString("email").contains("Введите правильный адрес"));

    }

    @Test (description = "with invalid e-mail: starts with \".\"")
    public void userRegistration_11(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_START_DOT);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(400);
        Assert.assertTrue(response.then().extract().jsonPath().getString("email").contains("Введите правильный адрес"));

    }

    @Test (description = "with invalid e-mail: domain part starts with .")
    public void userRegistration_12(){

        UserData userRegistration = new UserData(PASSWORD, USERNAME,EMAIL_DOM_START_DOT);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(userRegistration)
                .post("users/");

        response.then().log().all().statusCode(400);
        Assert.assertTrue(response.then().extract().jsonPath().getString("email").contains("Введите правильный адрес"));

    }


}


//    @Test
//    public void userRegisrSuccess() {
//        Specifications.installSpecification(Specifications.requestSpec(BASE_URI), Specifications.ResponseSpec200());
//        String email = EMAIL;
//        Integer id = 88;
//        String username = USERNAME;
//        UserRegister user = new UserRegister(email, username, PASSWORD);
//        SuccessReg successReg = given()
//                .body(user)
//                .when()
//                .post("users")
//                .then().log().all()
//                .extract().as(SuccessReg.class);
//
//        Assert.assertEquals(id, SuccessReg.getId());
//        Assert.assertEquals(email, SuccessReg.getEmail());
//        Assert.assertEquals(username, SuccessReg.getUsername());
//    }