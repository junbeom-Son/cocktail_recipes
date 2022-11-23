package com.example.cocktail;

import com.example.cocktail.domain.Cocktail;
import com.example.cocktail.domain.CocktailIngredient;
import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.repository.CocktailIngredientRepository;
import com.example.cocktail.repository.CocktailRepository;
import com.example.cocktail.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CocktailTest {

    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    CocktailIngredientRepository cocktailIngredientRepository;

    @Test
    public void createCocktailsAndIngredients() {
        Cocktail margarita = new Cocktail(11007L, "마가리따", "Margarita", "Cocktail glass",
                "마가리따입니다", "alcoholic");
        Cocktail apello = new Cocktail(15106L, "아펠로", "Apello", "Collins Glass",
                "아펠로 입니다.", "alcoholic");

        cocktailRepository.save(margarita);
        cocktailRepository.save(apello);

        Ingredient tequila = new Ingredient("테킬라", "Tequila", "테킬라 입니다.", null);
        Ingredient tripleSec = new Ingredient("트리플섹", "Triple Sec", "설명", null);
        Ingredient limeJuice = new Ingredient("라임 주스", "Lime Juice", "라임쥬스 입니다.", null);

        ingredientRepository.save(tequila);
        ingredientRepository.save(tripleSec);
        ingredientRepository.save(limeJuice);

        CocktailIngredient margaritaIngredient1 = new CocktailIngredient(margarita, tequila, "1 1/2 oz");
        CocktailIngredient margaritaIngredient2 = new CocktailIngredient(margarita, tripleSec, "1/2 oz");
        CocktailIngredient margaritaIngredient3 = new CocktailIngredient(margarita, limeJuice, "1 oz");

        cocktailIngredientRepository.save(margaritaIngredient1);
        cocktailIngredientRepository.save(margaritaIngredient2);
        cocktailIngredientRepository.save(margaritaIngredient3);
    }
}
