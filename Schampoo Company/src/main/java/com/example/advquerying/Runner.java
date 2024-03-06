package com.example.advquerying;

import com.example.advquerying.repository.ShampooRepository;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private final ShampooRepository shampooRepository;
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Runner(ShampooRepository shampooRepository, ShampooService shampooService, IngredientService ingredientService) {
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    public void run(String... args) {


//        this.shampooService.selectBySize(Size.MEDIUM)
//                .forEach(s -> System.out.println(s.getBrand() + " " + s.getSize() + " " + s.getPrice()));

//        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM,10)
//                .forEach(s -> System.out.println(s.getBrand() + " " + s.getSize() + " " + s.getPrice()));

//        this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5))
//                .forEach(s -> System.out.println(s.getBrand() + " " + s.getSize() + " " + s.getPrice()));

//        this.ingredientService.selectNameStartsWith("M")
//                .forEach(System.out::println);

//        this.ingredientService.selectInName(List.of("Lavender", "Herbs","Apple"))
//                .forEach(System.out::println);

//       int count = this.shampooService.countPriceLowerThan(BigDecimal.valueOf(8.5));
//       System.out.println(count);

//        this.shampooService.selectByIngredientsCount(2)
//                .forEach(s -> System.out.println(s.getBrand()));

//       this.ingredientService.deleteByName("Nettle"); //TODO remove CASCADE.ALL

//        this.ingredientService.increasePriceByPercentage(0.1);




    }
}
