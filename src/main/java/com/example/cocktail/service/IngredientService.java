package com.example.cocktail.service;

import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient findIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    /**
     * update ingredient
     * @param ingredientID
     * @param ingredient
     * @return updated ingredient if it's an existing ingredient
     * if it doesn't exist, return null
     */
    public Ingredient update(Long ingredientID, Ingredient ingredient) {
        if (!ingredientRepository.existsById(ingredientID)) {
            return null;
        }
        return ingredientRepository.save(ingredient);
    }
}
