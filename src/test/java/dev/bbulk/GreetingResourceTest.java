package dev.bbulk;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        for (int i = 0; i < 6; i++)
            given()
                    .when().get("/someTenant" + (i + 1) + "/hello")
                    .then()
                    .log().body()
                    .statusCode(200);
    }

}