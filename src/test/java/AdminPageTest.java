import entities.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import pageobject.AdminPage;
import org.testng.annotations.*;

import java.util.List;


import static io.restassured.RestAssured.*;
import static Config.Config.*;
import static Config.Credentials.*;

public class AdminPageTest extends AdminPage {

    @Test
    public void getUsersList(){
        List<User> users = given()
                .header("Authorization", "Token " + getAccessToken())
                .when()
                .contentType(ContentType.JSON)
                .get("users/")
                .then().log().all()
                .extract().jsonPath().getList(".", User.class);

        Assert.assertTrue(users.size() > 0,
                "Don't get users list");
    }

}