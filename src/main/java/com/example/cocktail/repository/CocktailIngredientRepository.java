package com.example.cocktail.repository;

import com.example.cocktail.domain.Cocktail;
import com.example.cocktail.domain.CocktailIngredient;
import com.example.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, CocktailIngredient.Id> {
    Optional<CocktailIngredient> findByCocktailAndIngredient(Cocktail cocktail, Ingredient ingredient);
    @Query(value = "select * from cocktail_ingredient where cocktail_id=?1 and ingredient_no=?2", nativeQuery = true)
    Optional<CocktailIngredient> findByCocktailAndIngredientNo(Long cocktailId, Long ingredientNo);
}
