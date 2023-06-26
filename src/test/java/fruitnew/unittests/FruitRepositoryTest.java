package fruitnew.unittests;

import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class FruitRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FruitRepository fruitRepository;

    @Test
    void testGetFruitById() {
        // Create a fruit and save it to the database
        Fruit apple = new Fruit("Apple");
        entityManager.persist(apple);
        entityManager.flush();

        // Retrieve the fruit by its ID using the repository
        Fruit retrievedFruit = fruitRepository.findById(apple.getId()).get(0);

        // Assert that the retrieved fruit is not null and has the same name
        assertNotNull(retrievedFruit);
        assertEquals("Apple", retrievedFruit.getName());
    }

    @Test
    void testGetFruitByName() {
        // Create a fruit and save it to the database
        Fruit apple = new Fruit("Apple");
        entityManager.persist(apple);
        entityManager.flush();

        // Retrieve the fruit by its ID using the repository
        Fruit retrievedFruit = fruitRepository.findFruitByName("Apple").get(0);

        // Assert that the retrieved fruit is not null and has the same name
        assertNotNull(retrievedFruit);
        assertEquals("Apple", retrievedFruit.getName());
    }

}
