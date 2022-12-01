package com.example.cocktail.repository;

import com.example.cocktail.domain.Cocktail;
import com.example.cocktail.domain.CocktailIngredient;
import com.example.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, CocktailIngredient.Id> {
    CocktailIngredient findByCocktailAndIngredient(Cocktail cocktail, Ingredient ingredient);
}
