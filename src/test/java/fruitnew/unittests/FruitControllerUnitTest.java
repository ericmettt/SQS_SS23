package fruitnew.unittests;

import fruitnew.controller.FruitController;
import fruitnew.model.Fruit;
import fruitnew.service.FruitService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@WebMvcTest(FruitController.class)
class FruitControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FruitService service;

    @Test
    void addFruitTest(){

        Mono<Fruit> fruitMono = Mono.just(new Fruit("Apple"));
        when(service.saveDetails(fruitMono.block())).thenReturn(fruitMono.block());

        webTestClient.post().uri("/addFruit").
                body(fruitMono,Fruit.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void indexPageTest() {
        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getAppleTest() {
        FruitService fruitService = mock(FruitService.class);
        when(fruitService.getFruitInformation("apple")).thenReturn(new Fruit("apple"));

        Fruit apple = new Fruit("apple");

        assertEquals(apple.getName(),fruitService.getFruitInformation("apple").getName());
    }

    @Test
    void getFruitInfoTest(){
        FruitService fruitService = mock(FruitService.class);
        when(fruitService.getFruitInformation("Melon")).thenReturn(new Fruit("Melon"));

        Fruit apple = new Fruit("Melon");

        assertEquals(apple.getName(),fruitService.getFruitInformation("Melon").getName());
    }

}