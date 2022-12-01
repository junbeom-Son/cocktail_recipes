package com.example.cocktail.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    Boolean modified;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastModified;
    @OneToMany(mappedBy = "cocktail")
    private Set<CocktailIngredient> cocktailIngredients = new HashSet<>();

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
        this.modified = false;
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
}
