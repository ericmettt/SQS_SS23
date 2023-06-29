package fruitnew.unittests;

import fruitnew.controller.FruitController;
import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import fruitnew.service.FruitService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
public class FruitServiceUnitTest {


    @Test
    public void testGetFruitInformation() {
        // Mock the WebClient response

        //fruitService.getFruitInformation("Fruit");

    }

}
