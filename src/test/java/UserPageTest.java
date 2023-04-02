import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.UserPage;
import static Config.Credentials.*;
import static io.restassured.RestAssured.*;

public class UserPageTest extends UserPage {

    @Test (description = "get user's info")
    public void getInfoMe_13(){
        Response response = given()
                .header("Authorization", "Token " + getAccessToken())
                .when()
                .get("users/me/");
        response.then().log().all().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().getString("username"), USERNAME );
    }

    @Test (description = "delete user")
    public void deleteUser_14(){
        String body = "{\n" +
                "    \"current_password\" : \"" + PASSWORD + "\"\n" +
                "}";
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + getAccessToken())
                .body(body)
                .when()
                .delete("users/me/");
        response.then().log().all().statusCode(204);
    }
}
