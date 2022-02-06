import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static specs.Specs.request;
import static specs.Specs.responseSpec;

public class ReqresTestsWithModels {

    @Test
    void loginSuccessfulTest() {
        //https://reqres.in/api/login
        String data = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}";

        given()
                .spec(request)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void getListUsersTest() {
        String response =
                get("api/users?page=2")
                        .then()
                        .statusCode(200)
                        .extract().path("data").toString();

        System.out.println(response);
    }

    @Test
    void getSingleUserTest() {
        get("api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2), "data.email", is("janet.weaver@reqres.in"),
                        "data.first_name", is("Janet"), "data.last_name", is("Weaver"),
                        "data.avatar", is("https://reqres.in/img/faces/2-image.jpg"),
                        "support.url", is("https://reqres.in/#support-heading"),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    void updateUserTest() {
        String putData = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";

        given()
                .contentType(JSON)
                .body(putData)
                .when()
                .put("api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"), "job", is("zion resident"));

    }
}
