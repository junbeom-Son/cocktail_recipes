package com.example.cocktail.repository;

import com.example.cocktail.domain.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    Cocktail findCocktailById(Long id);
}
