import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests extends TestBase {

    @Test
    void loginSuccessfulTest() {
        //https://reqres.in/api/login
        String data = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void loginNegativeTest() {
        //https://reqres.in/api/login
        String data = "{\"email\":\"eve.holt@reqres.in\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

    @Test
    void listUsersTest() {
        String response =
                get("api/users?page=2")
                .then()
                .statusCode(200)
                .extract().path("data").toString();

        System.out.println(response);
    }

}
