package com.example.cocktail.vo;

import com.example.cocktail.dto.CocktailDTO;
import lombok.Getter;

import java.util.Collections;
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
        if (cocktailDTO.getIngredient1() != null) {
            this.ingredients.put(1L, Collections.singletonMap(cocktailDTO.getIngredient1(), cocktailDTO.getMeasure1()));
        }
        if (cocktailDTO.getIngredient2() != null) {
            this.ingredients.put(2L, Collections.singletonMap(cocktailDTO.getIngredient2(), cocktailDTO.getMeasure2()));
        }
        if (cocktailDTO.getIngredient3() != null) {
            this.ingredients.put(3L, Collections.singletonMap(cocktailDTO.getIngredient3(), cocktailDTO.getMeasure3()));
        }
        if (cocktailDTO.getIngredient4() != null) {
            this.ingredients.put(4L, Collections.singletonMap(cocktailDTO.getIngredient4(), cocktailDTO.getMeasure4()));
        }
        if (cocktailDTO.getIngredient5() != null) {
            this.ingredients.put(5L, Collections.singletonMap(cocktailDTO.getIngredient5(), cocktailDTO.getMeasure5()));
        }
        if (cocktailDTO.getIngredient6() != null) {
            this.ingredients.put(6L, Collections.singletonMap(cocktailDTO.getIngredient6(), cocktailDTO.getMeasure6()));
        }
        if (cocktailDTO.getIngredient7() != null) {
            this.ingredients.put(7L, Collections.singletonMap(cocktailDTO.getIngredient7(), cocktailDTO.getMeasure7()));
        }
        if (cocktailDTO.getIngredient8() != null) {
            this.ingredients.put(8L, Collections.singletonMap(cocktailDTO.getIngredient8(), cocktailDTO.getMeasure8()));
        }
        if (cocktailDTO.getIngredient9() != null) {
            this.ingredients.put(9L, Collections.singletonMap(cocktailDTO.getIngredient9(), cocktailDTO.getMeasure9()));
        }
        if (cocktailDTO.getIngredient10() != null) {
            this.ingredients.put(10L, Collections.singletonMap(cocktailDTO.getIngredient10(), cocktailDTO.getMeasure10()));
        }
    }
}
