package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    IngredientRepository ingredientRepository;
    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectNameStartsWith(String start) {
        return ingredientRepository.findByNameStartingWith(start);
    }

    @Override
    public List<Ingredient> selectInName(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(names);
    }

    @Override
    @Transactional
    public int deleteByName(String name) {
        return this.ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void increasePriceByPercentage(double percent) {
        BigDecimal actualPercent = BigDecimal.valueOf(percent);
        this.ingredientRepository.increasePriceByPercentage(actualPercent);
    }
}
