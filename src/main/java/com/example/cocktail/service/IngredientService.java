package com.example.cocktail.service;

import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient findIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
