package com.example.cocktail.dto;

import lombok.Getter;

@Getter
public class IngredientDTO {
    Long id;
    String korName;
    String engName;
    String ingredientDescription;
    String imageSource;
    String ingredientType;

    public IngredientDTO(Long id, String korName, String engName, String ingredientDescription, String imageSource,
                         String ingredientType) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.ingredientDescription = ingredientDescription;
        this.imageSource = imageSource;
        this.ingredientType = ingredientType;
    }
}
