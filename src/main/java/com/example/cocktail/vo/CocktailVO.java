package com.example.cocktail.vo;

import com.example.cocktail.dto.CocktailDTO;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CocktailVO {
    private final String INGREDIENT_PREFIX = "ingredient";
    private final String MEASURE_PREFIX = "measure";
    private final Long id;
    private final String korName;
    private final String engName;
    private final String alcoholic;
    private final String glass;
    private final String cocktailDescription;
    private final Map<String, String> ingredients = new HashMap<>();
    private final Map<String, String> measures = new HashMap<>();

    public CocktailVO(CocktailDTO cocktailDTO) {
        this.id = cocktailDTO.getId();
        this.korName = cocktailDTO.getKorName();
        this.engName = cocktailDTO.getEngName();
        this.alcoholic = cocktailDTO.getAlcoholic();
        this.glass = cocktailDTO.getGlass();
        this.cocktailDescription = cocktailDTO.getCocktailDescription();
        this.ingredients.put(INGREDIENT_PREFIX + 1, cocktailDTO.getIngredient1());
        this.ingredients.put(INGREDIENT_PREFIX + 2, cocktailDTO.getIngredient2());
        this.ingredients.put(INGREDIENT_PREFIX + 3, cocktailDTO.getIngredient3());
        this.ingredients.put(INGREDIENT_PREFIX + 4, cocktailDTO.getIngredient4());
        this.ingredients.put(INGREDIENT_PREFIX + 5, cocktailDTO.getIngredient5());
        this.ingredients.put(INGREDIENT_PREFIX + 6, cocktailDTO.getIngredient6());
        this.ingredients.put(INGREDIENT_PREFIX + 7, cocktailDTO.getIngredient7());
        this.ingredients.put(INGREDIENT_PREFIX + 8, cocktailDTO.getIngredient8());
        this.ingredients.put(INGREDIENT_PREFIX + 9, cocktailDTO.getIngredient9());
        this.ingredients.put(INGREDIENT_PREFIX + 10, cocktailDTO.getIngredient10());
        this.measures.put(MEASURE_PREFIX + 1, cocktailDTO.getMeasure1());
        this.measures.put(MEASURE_PREFIX + 2, cocktailDTO.getMeasure2());
        this.measures.put(MEASURE_PREFIX + 3, cocktailDTO.getMeasure3());
        this.measures.put(MEASURE_PREFIX + 4, cocktailDTO.getMeasure4());
        this.measures.put(MEASURE_PREFIX + 5, cocktailDTO.getMeasure5());
        this.measures.put(MEASURE_PREFIX + 6, cocktailDTO.getMeasure6());
        this.measures.put(MEASURE_PREFIX + 7, cocktailDTO.getMeasure7());
        this.measures.put(MEASURE_PREFIX + 8, cocktailDTO.getMeasure8());
        this.measures.put(MEASURE_PREFIX + 9, cocktailDTO.getMeasure9());
        this.measures.put(MEASURE_PREFIX + 10, cocktailDTO.getMeasure10());
    }
}
