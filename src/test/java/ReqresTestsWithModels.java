import models.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.request;
import static specs.Specs.responseSpec;

public class ReqresTestsWithModels {

    @Test
    void loginSuccessfulTest() {
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
        String response = given()
                .spec(request)
                .get("/users?page=2")
                .then()
                .spec(responseSpec)
                .extract().path("data").toString();

        System.out.println(response);
    }

    @Test
    @DisplayName("Single User test with lombok model")
    void getSingleUser() {
        UserData data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .log()
                .body()
                .extract().as(UserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals("Janet", data.getUser().getFirstName());

    }

    @Test
    void updateUserTest() {
        String putData = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";

        given()
                .spec(request)
                .body(putData)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec)
                .body("name", is("morpheus"), "job", is("zion resident"));

    }
}
