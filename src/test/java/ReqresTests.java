import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests extends TestBase {

    @Test
    void loginSuccessfulTest() {
        //https://reqres.in/api/login
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }
}
