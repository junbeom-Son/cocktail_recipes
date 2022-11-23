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
    Boolean modified;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastModified;
    @OneToMany(mappedBy = "cocktail")
    private Set<CocktailIngredient> cocktailIngredients = new HashSet<>();

    public Cocktail(Long id, String korName, String engName, String glass, String cocktailDescription, String alcoholic) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.glass = glass;
        this.cocktailDescription = cocktailDescription;
        this.alcoholic = alcoholic;
        this.modified = false;
    }
}
