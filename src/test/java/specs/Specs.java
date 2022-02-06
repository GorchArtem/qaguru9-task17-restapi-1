package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class Specs {
    public static RequestSpecification requestLogin = with()
            .contentType(JSON)
            .baseUri("https://reqres.in/")
            .basePath("api/login")
            .log().all();

    public static ResponseSpecBuilder responseLogin = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("token", is("QpwL5tke4Pnpja7X4"));

}
