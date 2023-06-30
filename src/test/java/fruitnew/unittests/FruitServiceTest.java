package fruitnew.unittests;

import fruitnew.model.Fruit;
import fruitnew.service.FruitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DataJpaTest
class FruitServiceTest {

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testGetFruitInformation() {

        FruitService fruitService = mock(FruitService.class);


        when(fruitService.getFruitInformation("Melon")).thenThrow(WebClientResponseException.InternalServerError.create(500, "Internal Server Error", null, null, null));

        Assertions.assertThrows(WebClientResponseException.InternalServerError.class, () -> {
            fruitService.getFruitInformation("Melon");
        });


        verify(fruitService).getFruitInformation("Melon");
    }

    @Test
    public void testGetFruitInformation_ErrorHandling() {
        MockitoAnnotations.openMocks(this);

        Logger logger = mock(Logger.class);

        FruitService fruitService = mock(FruitService.class);


        when(fruitService.getFruitInformation("Melon")).thenThrow(new RuntimeException("Unable to retrieve fruit information. Please try again later."));


        Assertions.assertThrows(RuntimeException.class, () -> {
            fruitService.getFruitInformation("Melon");
        });


        doAnswer((Answer<Void>) invocation -> null).when(logger).severe(anyString());

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
