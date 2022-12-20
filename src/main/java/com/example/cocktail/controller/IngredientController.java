package com.example.cocktail.controller;

import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.service.IngredientService;
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

    @PostMapping("/{ingredientID}")
    public Ingredient editIngredient(@PathVariable Long ingredientID, @RequestBody Ingredient ingredient) {
        return ingredientService.update(ingredientID, ingredient);
    }
}
