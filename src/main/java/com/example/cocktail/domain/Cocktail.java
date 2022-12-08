package com.example.cocktail.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cocktail {
    @Id
    Long id;
    String korName;
    String engName;
    String glass;
    @Column(length = 2000)
    String cocktailDescription;
    String alcoholic;
    String imageSource;
    String imageAttribution;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastModified;
    @OneToMany(
            mappedBy = "cocktail",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
    )
    private List<CocktailIngredient> cocktailIngredients = new ArrayList<>();

    public Cocktail(Long id, String korName, String engName, String glass, String cocktailDescription,
                    String alcoholic, String imageSource, String imageAttribution) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.glass = glass;
        this.cocktailDescription = cocktailDescription;
        this.alcoholic = alcoholic;
        this.imageSource = imageSource;
        this.imageAttribution = imageAttribution;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setCocktailDescription(String cocktailDescription) {
        this.cocktailDescription = cocktailDescription;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public void setImageAttribution(String imageAttribution) {
        this.imageAttribution = imageAttribution;
    }

    public void addIngredient(CocktailIngredient cocktailIngredient) {
        cocktailIngredients.add(cocktailIngredient);
    }

    /**
     * ingredientNo 가 일치하는 재료를 반환한다.
     * @param ingredientNo
     * @return 있으면 존재하는 cocktailIngredient, 없다면 null return
     * null 을 리턴할 수 있기 때문에 사용하는 쪽에서 주의해야한다.
     */
    public CocktailIngredient getIngredientByNo(Long ingredientNo) {
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            if (cocktailIngredient.getId().getIngredientNo() == ingredientNo) {
                return cocktailIngredient;
            }
        }
        return null;
    }

    public CocktailIngredient getIngredientById(Long ingredientId) {
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            if (cocktailIngredient.getId().getIngredientId() == ingredientId) {
                return cocktailIngredient;
            }
        }
        return null;
    }

    public void removeIngredient(CocktailIngredient duplicatedIngredient) {
        Long ingredientNo = duplicatedIngredient.getId().getIngredientNo();
        cocktailIngredients.remove(duplicatedIngredient);
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            if (cocktailIngredient.getId().getIngredientNo() > ingredientNo) {
                cocktailIngredient.getId().setIngredientNo(cocktailIngredient.getId().getIngredientNo() - 1);
            }
        }
    }
}
