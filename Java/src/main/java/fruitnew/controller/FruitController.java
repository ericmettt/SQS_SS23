package fruitnew.controller;

import fruitnew.model.Fruit;
import fruitnew.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class FruitController {

    @Autowired
    private FruitService fruitService;



    @GetMapping({"/"})
    public Fruit getFruitInfo() {
        return this.fruitService.getFruitInformation("apple");
    }

    @PostMapping({"/addFruit"})
    public Fruit postDetails(@RequestBody Fruit fruit) {
        Fruit f = this.fruitService.getFruitInformation(fruit.getName());
        return this.fruitService.saveDetails(f);
    }
}