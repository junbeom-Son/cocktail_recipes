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

import java.util.*;

@Service
public class CocktailService {

    private final int ONE = 1;

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
    public int save(CocktailDTO cocktailDTO) {
        saveCocktail(cocktailDTO);
        saveIngredients(cocktailDTO);
        saveIngredientsMeasure(cocktailDTO);
        return ONE;
    }

    @Transactional
    public int saveCocktails(List<CocktailDTO> cocktailDTOs) {
        int count = 0;
        for (CocktailDTO cocktailDTO : cocktailDTOs) {
            count += save(cocktailDTO);
        }
        return count;
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
                    cocktailDTO.getCocktailDescription(), cocktailDTO.getAlcoholic(),
                    cocktailDTO.getImageSource(), cocktailDTO.getImageAttribution()));
        }
    }

    /**
     * 칵테일의 재료들을 저장하는 메서드, 각각의 재료를 저장하기 위해 하나의 재료를 저장하는 메소드를 호출
     * @param cocktailDTO
     */
    private void saveIngredients(CocktailDTO cocktailDTO) {
        saveIngredient(cocktailDTO.getIngredient1());
        saveIngredient(cocktailDTO.getIngredient2());
        saveIngredient(cocktailDTO.getIngredient3());
        saveIngredient(cocktailDTO.getIngredient4());
        saveIngredient(cocktailDTO.getIngredient5());
        saveIngredient(cocktailDTO.getIngredient6());
        saveIngredient(cocktailDTO.getIngredient7());
        saveIngredient(cocktailDTO.getIngredient8());
        saveIngredient(cocktailDTO.getIngredient9());
        saveIngredient(cocktailDTO.getIngredient10());
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
        Ingredient ingredient = ingredientRepository.findByEngName(engName).orElse(null);
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
    private void saveIngredientsMeasure(CocktailDTO cocktailDTO) {
        Cocktail cocktail = cocktailRepository.findById(cocktailDTO.getId()).get();
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient1(), cocktailDTO.getMeasure1(), 1L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient2(), cocktailDTO.getMeasure2(), 2L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient3(), cocktailDTO.getMeasure3(), 3L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient4(), cocktailDTO.getMeasure4(), 4L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient5(), cocktailDTO.getMeasure5(), 5L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient6(), cocktailDTO.getMeasure6(), 6L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient7(), cocktailDTO.getMeasure7(), 7L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient8(), cocktailDTO.getMeasure8(), 8L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient9(), cocktailDTO.getMeasure9(), 9L);
        saveIngredientMeasure(cocktail, cocktailDTO.getIngredient10(), cocktailDTO.getMeasure10(), 10L);

    }

    /**
     * 앞서 ingredientEngName 으로 ingredient 를 저장했기 때문에,
     * findByEngName 이 항상 null 을 return 하지 않음을 보장한다.
     * @param cocktail
     * @param ingredientEngName
     * @param measure
     */
    private void saveIngredientMeasure(Cocktail cocktail, String ingredientEngName, String measure, Long ingredientNo) {
        if (ingredientEngName == null) {
            return;
        }
        Ingredient ingredient = ingredientRepository.findByEngName(ingredientEngName).orElse(null);
        CocktailIngredient cocktailIngredient = cocktailIngredientRepository
                .findByCocktailAndIngredient(cocktail, ingredient);
        if (cocktailIngredient == null) {
            cocktailIngredientRepository.save(new CocktailIngredient(cocktail, ingredient, measure, ingredientNo));
        }
    }

    public Cocktail findCocktailById(Long id) {
        return cocktailRepository.findById(id).orElse(null);
    }

    public CocktailDTO findCocktailDTOById(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id).orElse(null);
        if (cocktail == null) {
            return null;
        }
        return createCocktailDTO(cocktail);
    }

    @Transactional
    public List<CocktailDTO> findAllCocktailDTOs() {
        List<Cocktail> cocktails = cocktailRepository.findAll();
        List<CocktailDTO> cocktailDTOs = new ArrayList<>();
        for (Cocktail cocktail : cocktails) {
            cocktailDTOs.add(createCocktailDTO(cocktail));
        }
        return cocktailDTOs;
    }

    private CocktailDTO createCocktailDTO(Cocktail cocktail) {
        Map<String, String> ingredients = extractIngredients(cocktail);
        Map<String, String> measures = extractMeasures(cocktail);
        return new CocktailDTO(cocktail.getId(), cocktail.getKorName(), cocktail.getEngName(),
                cocktail.getAlcoholic(), cocktail.getGlass(), cocktail.getCocktailDescription(),
                ingredients.get("ingredient1"), ingredients.get("ingredient2"), ingredients.get("ingredient3"),
                ingredients.get("ingredient4"), ingredients.get("ingredient5"), ingredients.get("ingredient6"),
                ingredients.get("ingredient7"), ingredients.get("ingredient8"), ingredients.get("ingredient9"),
                ingredients.get("ingredient10"), measures.get("measure1"), measures.get("measure2"),
                measures.get("measure3"), measures.get("measure4"), measures.get("measure5"), measures.get("measure6"),
                measures.get("measure7"), measures.get("measure8"), measures.get("measure9"),measures.get("measure10"),
                cocktail.getImageSource(), cocktail.getImageAttribution());
    }

    private Map<String, String> extractIngredients(Cocktail cocktail) {
        String prefix = "ingredient";
        Map<String, String> ingredients = new HashMap<>();
        Set<CocktailIngredient> cocktailIngredients = cocktail.getCocktailIngredients();
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            Ingredient ingredient = cocktailIngredient.getIngredient();
            ingredients.put(prefix + cocktailIngredient.getId().getIngredientNo(), ingredient.getEngName());
        }
        return ingredients;
    }

    private Map<String, String> extractMeasures(Cocktail cocktail) {
        String prefix = "measure";
        Map<String, String> measures = new HashMap<>();
        Set<CocktailIngredient> cocktailIngredients = cocktail.getCocktailIngredients();
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            measures.put(prefix + cocktailIngredient.getId().getIngredientNo(), cocktailIngredient.getMeasure());
        }
        return measures;
    }

    @Transactional
    public int update(Long cocktailID, CocktailDTO cocktailDTO) {
        int count = 0;
        if (!cocktailRepository.existsById(cocktailID)) {
            return count;
        }
        updateCocktail(cocktailDTO);
        saveIngredients(cocktailDTO);
        updateIngredientsMeasure(cocktailDTO);
        count = 1;
        return count;
    }

    @Transactional
    public int updateCocktails(List<CocktailDTO> cocktailDTOs) {
        int count = 0;
        for (CocktailDTO cocktailDTO : cocktailDTOs) {
            count += update(cocktailDTO.getId(), cocktailDTO);
        }
        return count;
    }

    private void updateCocktail(CocktailDTO cocktailDTO) {
        Cocktail cocktail = cocktailRepository.findById(cocktailDTO.getId()).get();
        cocktail.setKorName(cocktailDTO.getKorName());
        cocktail.setEngName(cocktailDTO.getEngName());
        cocktail.setGlass(cocktailDTO.getGlass());
        cocktail.setCocktailDescription(cocktailDTO.getCocktailDescription());
        cocktail.setAlcoholic(cocktailDTO.getAlcoholic());
        cocktail.setImageSource(cocktailDTO.getImageSource());
        cocktail.setImageAttribution(cocktailDTO.getImageAttribution());
        cocktailRepository.save(cocktail);
    }

    private void updateIngredientsMeasure(CocktailDTO cocktailDTO) {
        Cocktail cocktail = cocktailRepository.findById(cocktailDTO.getId()).get();
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient1(), cocktailDTO.getMeasure1(), 1L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient2(), cocktailDTO.getMeasure2(), 2L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient3(), cocktailDTO.getMeasure3(), 3L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient4(), cocktailDTO.getMeasure4(), 4L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient5(), cocktailDTO.getMeasure5(), 5L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient6(), cocktailDTO.getMeasure6(), 6L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient7(), cocktailDTO.getMeasure7(), 7L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient8(), cocktailDTO.getMeasure8(), 8L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient9(), cocktailDTO.getMeasure9(), 9L);
        updateIngredientMeasure(cocktail, cocktailDTO.getIngredient10(), cocktailDTO.getMeasure10(), 10L);
    }

    private void updateIngredientMeasure(Cocktail cocktail, String ingredientEngName, String measure, Long ingredientNo) {
        if (ingredientEngName == null) {
            return;
        }
        Ingredient tmp = ingredientRepository.findByEngName(ingredientEngName).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(tmp.getId()).get();
        CocktailIngredient.Id id = new CocktailIngredient.Id(cocktail.getId(), ingredient.getId(), ingredientNo);
        CocktailIngredient cocktailIngredient = cocktailIngredientRepository
                .findById(id).get();
        if (cocktailIngredient == null) {
            System.out.println("null " + ingredientEngName);
            cocktailIngredientRepository.save(new CocktailIngredient(cocktail, ingredient, measure, ingredientNo));
        } else {
            System.out.println("before measure edit : " + cocktailIngredient.getMeasure());
            cocktailIngredient.setIngredient(ingredient);
            cocktailIngredient.setMeasure(measure);
            CocktailIngredient savedMeasure = cocktailIngredientRepository.saveAndFlush(cocktailIngredient);
            System.out.println("after measure edit" +savedMeasure.getMeasure());
        }
    }
}
