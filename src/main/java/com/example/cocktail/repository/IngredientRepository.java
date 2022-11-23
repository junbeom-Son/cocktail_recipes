package com.example.cocktail.repository;

import com.example.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByEngName(String engName);
}
