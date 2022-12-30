package com.example.cocktail.controller;

import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.dto.IngredientDTO;
import com.example.cocktail.service.IngredientService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/save")
    public IngredientDTO saveIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.saveIngredient(ingredientDTO);
    }
    /**
     * find ingredient by id
     * @param ingredientID
     * @return ingredient if it exists.
     * or return null
     */
    @GetMapping("/{ingredientID}")
    public Ingredient findIngredientById(@PathVariable Long ingredientID) {
        return ingredientService.findIngredientById(ingredientID);
    }

    @GetMapping
    public List<IngredientDTO> findAllIngredientDTOs() {
        return ingredientService.findAllIngredientDTOs();
    }

    @PostMapping("/edit/{ingredientID}")
    public Ingredient editIngredient(@PathVariable Long ingredientID, @RequestBody Ingredient ingredient) {
        return ingredientService.update(ingredientID, ingredient);
    }

    @PostMapping("/delete/{ingredientID}")
    public void deleteIngredient(@PathVariable Long ingredientID) {
        ingredientService.deleteById(ingredientID);
    }
}
