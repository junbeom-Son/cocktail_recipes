package com.example.cocktail.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
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

    String imageSource;

    @OneToMany(mappedBy = "ingredient")
    private Set<CocktailIngredient> cocktailIngredients = new HashSet<>();

    public Ingredient(String engName) {
        this.engName = engName;
    }

    public Ingredient(String korName, String engName, String ingredientDescription, Ingredient highLevelIngredient,
                      String imageSource) {
        this.korName = korName;
        this.engName = engName;
        this.ingredientDescription = ingredientDescription;
        this.highLevelIngredient = highLevelIngredient;
        this.imageSource = imageSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
