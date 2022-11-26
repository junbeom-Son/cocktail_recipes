package com.example.cocktail.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CocktailDTO {
    private Long id;
    private String korName;
    private String engName;
    private String alcoholic;
    private String glass;
    private String cocktailDescription;
    private Map<String, String> ingredients;
    private String imageSource;
    private String imageAttribution;

    public CocktailDTO(Long id, String korName, String engName, String alcoholic, String glass,
                       String cocktailDescription, String ingredient1, String ingredient2, String ingredient3,
                       String ingredient4,String ingredient5, String ingredient6, String ingredient7,
                       String ingredient8, String ingredient9, String ingredient10,
                       String measure1, String measure2, String measure3, String measure4, String measure5,
                       String measure6, String measure7, String measure8, String measure9, String measure10,
                       String imageSource, String imageAttribution) {
        this.ingredients = new HashMap<>();
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.alcoholic = alcoholic;
        this.glass = glass;
        this.cocktailDescription = cocktailDescription;
        this.ingredients.put(ingredient1, measure1);
        this.ingredients.put(ingredient2, measure2);
        this.ingredients.put(ingredient3, measure3);
        this.ingredients.put(ingredient4, measure4);
        this.ingredients.put(ingredient5, measure5);
        this.ingredients.put(ingredient6, measure6);
        this.ingredients.put(ingredient7, measure7);
        this.ingredients.put(ingredient8, measure8);
        this.ingredients.put(ingredient9, measure9);
        this.ingredients.put(ingredient10, measure10);
        this.imageSource = imageSource;
        this.imageAttribution = imageAttribution;
    }
}