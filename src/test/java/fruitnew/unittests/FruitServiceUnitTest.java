package fruitnew.unittests;

import fruitnew.controller.FruitController;
import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import fruitnew.service.FruitService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class FruitServiceUnitTest {

    @Mock
    private FruitRepository fruitRepository;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testGetFruitInformation() {
        // Mock the WebClient response

        //fruitService.getFruitInformation("Fruit");

    }
    @Test
    void testSaveDetails() {

        Fruit apple = new Fruit("Apple");
        entityManager.persist(apple);
        entityManager.flush();
        entityManager.clear();

        // Assert
        Fruit savedApple = entityManager.find(Fruit.class, apple.getId());
        assertNotNull(savedApple, "The fruit should be saved");
    }
}
