package com.example.cocktail.service;

import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.dto.IngredientDTO;
import com.example.cocktail.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientDTO saveIngredient(IngredientDTO ingredientDTO) {
        Ingredient highLevelIngredient = ingredientRepository
                .findByEngName(ingredientDTO.getIngredientType()).orElse(null);
        Ingredient savedIngredient = ingredientRepository.save(new Ingredient(
                ingredientDTO.getKorName(), ingredientDTO.getEngName(),
                ingredientDTO.getIngredientDescription(), highLevelIngredient,
                ingredientDTO.getImageSource()));
        return null;
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

    public void deleteById(Long ingredientID) {
        if (ingredientRepository.existsById(ingredientID)) {
            ingredientRepository.deleteById(ingredientID);
        }
    }
}
