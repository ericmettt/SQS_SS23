package fruitnew.service;

import fruitnew.model.Fruit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class FruitServiceTest {

    @Autowired
    private TestEntityManager entityManager;
    @Test
    void getFruitInformation() {
    }

    @Test
    void saveDetails() {
        Fruit apple = new Fruit("Apple");
        entityManager.persist(apple);
        entityManager.flush();
        entityManager.clear();

        // Assert
        Fruit savedApple = entityManager.find(Fruit.class, apple.getId());
        assertNotNull(savedApple, "The fruit should be saved");
    }
}