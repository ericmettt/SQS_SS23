package fruitnew.service;

import com.google.gson.Gson;
import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FruitService {
    private final WebClient webClient = WebClient.create("https://fruityvice.com/");
    @Autowired
    private FruitRepository fruitRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public FruitService() {
    }

    public Fruit getFruitInformation(String fruitname) {
        String path = "/api/fruit/" + fruitname;
        String response = this.webClient.get().uri(path, new Object[0]).retrieve().bodyToMono(String.class).block();
        Gson gson = new Gson();
        return gson.fromJson(response, Fruit.class);
    }

    public Fruit saveDetails(Fruit fruit) {
        return this.fruitRepository.save(fruit);
    }
}
