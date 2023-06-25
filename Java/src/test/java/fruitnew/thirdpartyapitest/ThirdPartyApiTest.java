package fruitnew.thirdpartyapitest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

//Test https://fruityvice.com/api/fruit/apple
class ThirdPartyApiTest {

    int wireMockPort = 7777;
    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(wireMockPort));
    private static WebTestClient webTestClient;
    @BeforeEach
    public void startWiremock(){
        // Start the WireMock server
        wireMockServer.start();
        // Configure WireMock to respond to API requests
        configureFor("localhost", wireMockServer.port());

        stubFor(get(urlEqualTo("/api/fruit/apple"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"name\":\"Apple\",\"id\":6,\"family\":\"Rosaceae\",\"order\":\"Rosales\",\"genus\":\"Malus\",\"nutritions\":{\"calories\":52,\"fat\":0.4,\"sugar\":10.3,\"carbohydrates\":11.4,\"protein\":0.3}}]")));

        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + wireMockServer.port())
                .build();

    }
    @AfterEach
    void stopWiremock(){
        // Stop the WireMock server
        wireMockServer.stop();
    }
    @Test
    void testFruitApi() {
        webTestClient.get().uri("/api/fruit/apple")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Apple")
                .jsonPath("$[0].id").isEqualTo(6)
                .jsonPath("$[0].family").isEqualTo("Rosaceae")
                .jsonPath("$[0].order").isEqualTo("Rosales")
                .jsonPath("$[0].genus").isEqualTo("Malus")
                .jsonPath("$[0].nutritions.calories").isEqualTo(52)
                .jsonPath("$[0].nutritions.fat").isEqualTo(0.4)
                .jsonPath("$[0].nutritions.sugar").isEqualTo(10.3)
                .jsonPath("$[0].nutritions.carbohydrates").isEqualTo(11.4)
                .jsonPath("$[0].nutritions.protein").isEqualTo(0.3);
                /*
         */

    }

}
