package com.example.cocktail.dto;

import lombok.Getter;

@Getter
public class CocktailDTO {
    private Long id;
    private String korName;
    private String engName;
    private String alcoholic;
    private String glass;
    private String cocktailDescription;
    private String ingredient1;
    private String ingredient2;
    private String ingredient3;
    private String ingredient4;
    private String ingredient5;
    private String ingredient6;
    private String ingredient7;
    private String ingredient8;
    private String ingredient9;
    private String ingredient10;
    private String measure1;
    private String measure2;
    private String measure3;
    private String measure4;
    private String measure5;
    private String measure6;
    private String measure7;
    private String measure8;
    private String measure9;
    private String measure10;
    private String imageSource;
    private String imageAttribution;

    public CocktailDTO(Long id, String korName, String engName, String alcoholic, String glass,
                       String cocktailDescription, String ingredient1, String ingredient2, String ingredient3,
                       String ingredient4,String ingredient5, String ingredient6, String ingredient7,
                       String ingredient8, String ingredient9, String ingredient10,
                       String measure1, String measure2, String measure3, String measure4, String measure5,
                       String measure6, String measure7, String measure8, String measure9, String measure10,
                       String imageSource, String imageAttribution) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.alcoholic = alcoholic;
        this.glass = glass;
        this.cocktailDescription = cocktailDescription;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
        this.measure1 = measure1;
        this.measure2 = measure2;
        this.measure3 = measure3;
        this.measure4 = measure4;
        this.measure5 = measure5;
        this.measure6 = measure6;
        this.measure7 = measure7;
        this.measure8 = measure8;
        this.measure9 = measure9;
        this.measure10 = measure10;
        this.imageSource = imageSource;
        this.imageAttribution = imageAttribution;
    }
}