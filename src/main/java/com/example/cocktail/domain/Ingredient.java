package com.example.cocktail.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue
    Long id;
    String korName;
    String engName;
    @Column(length = 2000)
    String ingredientDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    Ingredient highLevelIngredient;

    @OneToMany(mappedBy = "ingredient")
    private Set<CocktailIngredient> cocktailIngredients = new HashSet<>();

    public Ingredient(String engName) {
        this.engName = engName;
    }

    public Ingredient(String korName, String engName, String ingredientDescription, Ingredient highLevelIngredient) {
        this.korName = korName;
        this.engName = engName;
        this.ingredientDescription = ingredientDescription;
        this.highLevelIngredient = highLevelIngredient;
    }
}
