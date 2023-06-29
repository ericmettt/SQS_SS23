package fruitnew.unittests;

import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import fruitnew.service.FruitService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@DataJpaTest
class FruitServiceTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    void testGetFruitInformation() {
        // Arrange
        FruitService fruitService = mock(FruitService.class);
        when(fruitService.getFruitInformation("Melon")).thenReturn(new Fruit("Melon"));
        when(fruitService.getFruitInformation("Melon")).thenReturn(new Fruit("Melon"));

        Fruit apple = new Fruit("Melon");

        assertEquals(apple.getName(),fruitService.getFruitInformation("Melon").getName());

    }

    @Test
    void testSaveDetails() {
        // Arrange
        Fruit apple = new Fruit("Apple");
        entityManager.persist(apple);
        entityManager.flush();
        entityManager.clear();

        // Assert
        Fruit savedApple = entityManager.find(Fruit.class, apple.getId());
        assertNotNull(savedApple, "The fruit should be saved");
    }
}
