package fruitnew.service;

import com.google.gson.Gson;
import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
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
        try {
            String path = "/api/fruit/" + fruitname;
            String response = this.webClient.get().uri(path, new Object[0]).retrieve().bodyToMono(String.class).block();
            Gson gson = new Gson();
            return gson.fromJson(response, Fruit.class);
        } catch (HttpServerErrorException ex) {
            // Log the error or handle the exception as per your requirement
            System.err.println("Error: Server error while calling the third-party API");
            // Optionally, you can throw a custom exception or return a fallback response
            throw new RuntimeException("Unable to retrieve fruit information. Please try again later.");
        }
    }

    public Fruit saveDetails(Fruit fruit) {
        return this.fruitRepository.save(fruit);
    }
}
