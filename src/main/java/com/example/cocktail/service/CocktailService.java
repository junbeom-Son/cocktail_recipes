package com.example.cocktail.service;

import com.example.cocktail.domain.Cocktail;
import com.example.cocktail.domain.CocktailIngredient;
import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.dto.CocktailDTO;
import com.example.cocktail.repository.CocktailIngredientRepository;
import com.example.cocktail.repository.CocktailRepository;
import com.example.cocktail.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CocktailService {

    @Autowired
    CocktailRepository cocktailRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    CocktailIngredientRepository cocktailIngredientRepository;

    /**
     * CocktailDTO를 입력받아, 칵테일과, 재료, 재료 수량을 각각 저장하는 메서드
     * 각각의 도메인을 저장하는 메서드 호출
     * @param cocktailDTO
     */
    @Transactional
    public void save(CocktailDTO cocktailDTO) {
        saveCocktail(cocktailDTO);
        saveIngredients(cocktailDTO);
        saveIngredientsPortion(cocktailDTO);
    }

    /**
     * CocktailDTO에서 데이터를 추출해 존재하지 않는 칵테일을 저장하는 메서드
     * @param cocktailDTO
     */
    private void saveCocktail(CocktailDTO cocktailDTO) {
        Cocktail cocktail = cocktailRepository.findById(cocktailDTO.getId()).orElse(null);
        if (cocktail == null) {
            cocktailRepository.save(new Cocktail(cocktailDTO.getId(), cocktailDTO.getKorName(),
                    cocktailDTO.getEngName(), cocktailDTO.getGlass(),
                    cocktailDTO.getCocktailDescription(), cocktailDTO.getAlcoholic()));
        }
    }

    /**
     * 칵테일의 재료들을 저장하는 메서드, 각각의 재료를 저장하기 위해 하나의 재료를 저장하는 메소드를 호출
     * @param cocktailDTO
     */
    private void saveIngredients(CocktailDTO cocktailDTO) {
        Map<String, String> ingredients = cocktailDTO.getIngredients();
        for (String ingredient : ingredients.keySet()) {
            saveIngredient(ingredient);
        }
    }

    /**
     * 하나의 재료를 저장하기 위한 메서드, 이미 존재하는 재료나, null 이라면 저장하지 않는다.
     * null 일 수 있는 이유는, 재료의 존재 유무와 상관없이 10가지를 한번에 주고받기 때문
     * @param engName
     */
    private void saveIngredient(String engName) {
        if (engName == null) {
            return;
        }
        Ingredient ingredient = ingredientRepository.findByEngName(engName);
        if (ingredient == null) {
            ingredientRepository.save(new Ingredient(engName));
        }
    }

    /**
     * 재료의 수량을 저장한다.
     * 앞서 cocktailDTO 로 전달된 id로 칵테일을 만들었으므로 항상 null 값을 리턴하지 않음을 보장한다.
     * 각 재료별로 저장하기위해 각각의 재료를 가지고 저장한다.
     * @param cocktailDTO
     */
    private void saveIngredientsPortion(CocktailDTO cocktailDTO) {
        Cocktail cocktail = cocktailRepository.findById(cocktailDTO.getId()).get();
        Map<String, String> ingredients = cocktailDTO.getIngredients();
        for (String ingredient : ingredients.keySet()) {
            saveIngredientPortion(cocktail, ingredient, ingredients.get(ingredient));
        }
    }

    /**
     * 앞서 ingredientEngName 으로 ingredient 를 저장했기 때문에,
     * findByEngName 이 항상 null 을 return 하지 않음을 보장한다.
     * @param cocktail
     * @param ingredientEngName
     * @param portion
     */
    private void saveIngredientPortion(Cocktail cocktail, String ingredientEngName, String portion) {
        if (ingredientEngName == null) {
            return;
        }
        Ingredient ingredient = ingredientRepository.findByEngName(ingredientEngName);
        CocktailIngredient cocktailIngredient = cocktailIngredientRepository
                .findByCocktailAndIngredient(cocktail, ingredient);
        if (cocktailIngredient == null) {
            cocktailIngredientRepository.save(new CocktailIngredient(cocktail, ingredient, portion));
        }
    }

    public Cocktail findCocktailById(Long id) {
        return cocktailRepository.findById(id).orElse(null);
    }
}
