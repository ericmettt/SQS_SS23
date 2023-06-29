package fruitnew.integrationstest;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// This class contains integration tests using RestAssured.
// It sends HTTP requests to the specified endpoints and validates the responses.
class IntegrationsTest {

    @Test
    void testCreateFruit() {
        // Set base URL and port
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        // Define request payload
        String requestBody = "{\"name\":\"Apple\",\"genus\":\"Malus\",\"family\":\"Rosaceae\"}";

        // Send POST request to "/users" endpoint with request payload and validate response
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/addFruit")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple"))
                .body("genus", equalTo("Malus"))
                .body("family", equalTo("Rosaceae"));
    }

    @Test
    void testGetFruit() {
        // Set base URL and port
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        // Send GET request to "/users/1" endpoint and validate response
        given()
                .contentType("application/json")
                .when()
                .get("")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple"))
                .body("genus", equalTo("Malus"))
                .body("family", equalTo("Rosaceae"));
    }


}
