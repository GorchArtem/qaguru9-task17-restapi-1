import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.request;
import static specs.Specs.responseSpec;

public class ReqresTestsWithModels {

    @Test
    void loginSuccessfulTest() {
//        String data = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}";
        String email = "eve.holt@reqres.in";
        String password = "cityslicka";
        String token = "QpwL5tke4Pnpja7X4";

        LoginUserRequest data = new LoginUserRequest(email, password);

        LoginUserResponse response = given()
                .spec(request)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .log()
                .body()
                .extract().as(LoginUserResponse.class);

        assertEquals(token, response.getToken());

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
        assertEquals("Weaver", data.getUser().getLastName());

    }

    @Test
    @DisplayName("Update User test with lombok model")
    void updateUserTest() {
        String name = "morpheus";
        String job = "zion";

        UpdateUserRequest updateUser = new UpdateUserRequest(name, job);

        UpdateUserResponse userResponse = given()
                .spec(request)
                .body(updateUser)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec)
                .log()
                .body().extract().as(UpdateUserResponse.class);

        assertEquals(userResponse.getName(), updateUser.getName());
        assertEquals(userResponse.getJob(), updateUser.getJob());


    }
}
