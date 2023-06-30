package fruitnew.integrationstest;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// This class contains integration tests using RestAssured.
// It sends HTTP requests to the specified endpoints and validates the responses.
//The Database and the Springboot service must be running for these Tests to work
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
        RestAssured.baseURI = "http://localhost/getApple";
        RestAssured.port = 8080;

        // Send GET request to "/Apple" endpoint and validate response
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
