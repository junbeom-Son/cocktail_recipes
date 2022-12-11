package com.example.cocktail.vo;

import com.example.cocktail.dto.CocktailDTO;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CocktailVO {
    private final Long id;
    private final String korName;
    private final String engName;
    private final String alcoholic;
    private final String glass;
    private final String cocktailDescription;
    private final Map<Long, Map<String, String>> ingredients = new HashMap<>();

    public CocktailVO(CocktailDTO cocktailDTO) {
        this.id = cocktailDTO.getId();
        this.korName = cocktailDTO.getKorName();
        this.engName = cocktailDTO.getEngName();
        this.alcoholic = cocktailDTO.getAlcoholic();
        this.glass = cocktailDTO.getGlass();
        this.cocktailDescription = cocktailDTO.getCocktailDescription();
        this.ingredients.put(1L, Map.of(cocktailDTO.getIngredient1(), cocktailDTO.getMeasure1()));
        this.ingredients.put(2L, Map.of(cocktailDTO.getIngredient2(), cocktailDTO.getMeasure2()));
        this.ingredients.put(3L, Map.of(cocktailDTO.getIngredient3(), cocktailDTO.getMeasure3()));
        this.ingredients.put(4L, Map.of(cocktailDTO.getIngredient4(), cocktailDTO.getMeasure4()));
        this.ingredients.put(5L, Map.of(cocktailDTO.getIngredient5(), cocktailDTO.getMeasure5()));
        this.ingredients.put(6L, Map.of(cocktailDTO.getIngredient6(), cocktailDTO.getMeasure6()));
        this.ingredients.put(7L, Map.of(cocktailDTO.getIngredient7(), cocktailDTO.getMeasure7()));
        this.ingredients.put(8L, Map.of(cocktailDTO.getIngredient8(), cocktailDTO.getMeasure8()));
        this.ingredients.put(9L, Map.of(cocktailDTO.getIngredient9(), cocktailDTO.getMeasure9()));
        this.ingredients.put(10L, Map.of(cocktailDTO.getIngredient10(), cocktailDTO.getMeasure10()));
    }
}
