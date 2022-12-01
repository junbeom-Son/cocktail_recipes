package com.example.cocktail.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "COCKTAIL_INGREDIENT")
@Getter
//@org.hibernate.annotations.Immutable
public class CocktailIngredient {
    @Getter
    @AllArgsConstructor
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "COCKTAIL_ID")
        private Long cocktailId;
        @Column(name = "INGREDIENT_ID")
        private Long ingredientId;

        @Column(name = "INGREDIENT_NO")
        private Long ingredientNo;

        public Id(){}

        @Override
        public int hashCode() {
            return Objects.hash(cocktailId, ingredientId, ingredientNo);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Id id = (Id) obj;
            return cocktailId.equals(id.cocktailId) && ingredientId.equals(id.ingredientId) && ingredientNo.equals(id.ingredientNo);
        }
    }
    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(
            name = "COCKTAIL_ID",
            insertable = false, updatable = false
    )
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(
            name = "INGREDIENT_ID",
            insertable = false, updatable = false
    )
    private Ingredient ingredient;
    private String measure;

    public CocktailIngredient(Cocktail cocktail, Ingredient ingredient, String portion, Long ingredientNo) {
        this.cocktail = cocktail;
        this.ingredient = ingredient;
        this.id.cocktailId = cocktail.getId();
        this.id.ingredientId = ingredient.getId();
        this.measure = portion;
        this.id.ingredientNo = ingredientNo;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
