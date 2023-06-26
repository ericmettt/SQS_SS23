package fruitnew.service;

import com.google.gson.Gson;
import fruitnew.model.Fruit;
import fruitnew.repository.FruitRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.logging.Logger;


@Service
public class FruitService {


    private final WebClient webClient = WebClient.create("https://fruityvice.com/");
    @Autowired
    private FruitRepository fruitRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = Logger.getLogger(FruitService.class.getName());


    public Fruit getFruitInformation(String fruitname) {
        try {
            String path = "/api/fruit/" + fruitname;
            String response = this.webClient.get().uri(path, (Object) new Object[0]).retrieve().bodyToMono(String.class).block();
            Gson gson = new Gson();
            return gson.fromJson(response, Fruit.class);
        } catch (WebClientResponseException.InternalServerError ex) {
            // Log the error or handle the exception as per your requirement
            logger.severe("Error: Internal Server Error from the third-party API");
            // Optionally, you can throw a custom exception or return a fallback response
            throw new RuntimeException("Unable to retrieve fruit information. Please try again later.");
        }
    }

    public Fruit saveDetails(Fruit fruit) {
        return this.fruitRepository.save(fruit);
    }
}
