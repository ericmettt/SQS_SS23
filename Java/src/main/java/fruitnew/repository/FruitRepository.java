package fruitnew.repository;

import java.util.List;

import fruitnew.model.Fruit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Integer> {
    List<Fruit> findById(long id);

    List<Fruit> findFruitByName(String name);

}
