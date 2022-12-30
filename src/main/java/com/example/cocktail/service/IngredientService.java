package com.example.cocktail.service;

import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.dto.IngredientDTO;
import com.example.cocktail.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return new IngredientDTO(savedIngredient.getId(), savedIngredient.getKorName(), savedIngredient.getEngName(),
                savedIngredient.getIngredientDescription(), savedIngredient.getImageSource(),
                savedIngredient.getHighLevelIngredient().getEngName());
    }

    public IngredientDTO findIngredientById(Long id) {
        if (!ingredientRepository.existsById(id)) {
            return null;
        }
        Ingredient ingredient = ingredientRepository.findById(id).get();
        return convertIngredientToIngredientDTO(ingredient);
    }

    @Transactional
    public List<IngredientDTO> findAllIngredientDTOs() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDTO> ingredientDTOs = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ingredientDTOs.add(convertIngredientToIngredientDTO(ingredient));
        }
        return ingredientDTOs;
    }

    private IngredientDTO convertIngredientToIngredientDTO(Ingredient ingredient) {
        if (ingredient.getHighLevelIngredient() == null) {
            return new IngredientDTO(ingredient.getId(), ingredient.getKorName(), ingredient.getEngName(),
                    ingredient.getIngredientDescription(), ingredient.getImageSource(),
                    null);
        }
        return new IngredientDTO(ingredient.getId(), ingredient.getKorName(), ingredient.getEngName(),
                ingredient.getIngredientDescription(), ingredient.getImageSource(),
                ingredient.getHighLevelIngredient().getEngName());
    }

    /**
     * update ingredient
     * @param ingredientID
     * @param ingredientDTO
     * @return updated ingredient if it's an existing ingredient
     * if it doesn't exist, return null
     */
    public IngredientDTO update(Long ingredientID, IngredientDTO ingredientDTO) {
        if (!ingredientRepository.existsById(ingredientID)) {
            return null;
        }
        Ingredient ingredient = ingredientRepository.findById(ingredientID).get();
        ingredient.setKorName(ingredientDTO.getKorName());
        ingredient.setEngName(ingredientDTO.getEngName());
        ingredient.setIngredientDescription(ingredientDTO.getIngredientDescription());
        ingredient.setImageSource(ingredientDTO.getImageSource());
        if (ingredientDTO.getIngredientType() == null) {
            ingredient.setHighLevelIngredient(null);
        } else {
            Ingredient highLevelIngredient = ingredientRepository.findByEngName(ingredientDTO.getIngredientType())
                    .orElse(null);
            ingredient.setHighLevelIngredient(highLevelIngredient);
        }
        ingredientRepository.save(ingredient);
        return convertIngredientToIngredientDTO(ingredient);
    }

    public void deleteById(Long ingredientID) {
        if (ingredientRepository.existsById(ingredientID)) {
            ingredientRepository.deleteById(ingredientID);
        }
    }
}
