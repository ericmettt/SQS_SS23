package fruitnew.IntegrationsTest;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//Integration Test via Rest Assured
public class Integrationstest {

    @Test
    public void testCreateFruit() {
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
    public void testGetUser() {
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
