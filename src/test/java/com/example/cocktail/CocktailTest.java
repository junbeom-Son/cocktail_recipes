package com.example.cocktail;

import com.example.cocktail.domain.Cocktail;
import com.example.cocktail.domain.CocktailIngredient;
import com.example.cocktail.domain.Ingredient;
import com.example.cocktail.dto.CocktailDTO;
import com.example.cocktail.service.CocktailService;
import com.example.cocktail.service.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class CocktailTest {

    @Autowired
    CocktailService cocktailService;

    @Autowired
    IngredientService ingredientService;

    @Test
    public void createCocktailsAndIngredients() {
        List<CocktailDTO> cocktailDTOs = new ArrayList<>();
        CocktailDTO margaritaDTO = new CocktailDTO(11007L, "마가리따", "Margarita",
                "Alcoholic", "Cocktail glass", "마가리따에요.", "Tequila",
                "Triple sec", "Lime juice", "Salt", null, null,
                null, null, null, null, "1 1/2 oz", "1/2 oz",
                "1 oz", null, null, null, null, null,null,
                null, "https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg",
                "Cocktailmarler");

        CocktailDTO thaiCoffeeDTO = new CocktailDTO(12782L, null, "Thai Coffee",
                "Non alcoholic", "Highball glass","Place the coffee and spices in the " +
                "filter cone of your coffee maker. Brew coffee as usual, let it cool. In a tall glass, dissolve 1 or 2 " +
                "teaspoons of sugar in an ounce of the coffee (it's easier to dissolve than if you put it right over ice). " +
                "Add 5-6 ice cubes and pour coffee to within about 1 inch of the top of the glass. " +
                "Rest a spoon on top of the coffee and slowly pour whipping cream into the spoon. " +
                "This will make the cream float on top of the coffee rather than dispersing into it right away.",
                "Coffee", "Coriander", "Cardamom", "Sugar",
                "Whipping cream", "Ice", null, null, null, null,
                "6 tblsp ground", "1/4 tsp", "4-5 whole green", null, null,
                null, null, null, null, null,
                "https://www.thecocktaildb.com/images/media/drink/wquwxs1441247025.jpg", null);

        CocktailDTO zorroDTO = new CocktailDTO(15328L, null, "Zorro", "Alcoholic",
                "Coffee Mug", "add all and pour black coffee and add whipped cream on top",
                "Sambuca", "Baileys irish cream", "White Creme de Menthe", "salt",
                null, null, null, null, null, null,
                "2 cl", "2 cl", "2 cl", null, null, null,
                null,null, null, null,
                "https://www.thecocktaildb.com/images/media/drink/kvvd4z1485621283.jpg", null);

        cocktailDTOs.add(margaritaDTO);
        cocktailDTOs.add(thaiCoffeeDTO);
        cocktailDTOs.add(zorroDTO);
        cocktailService.saveCocktails(cocktailDTOs);
    }

    @Transactional
    @Test
    public void findIngredients() {
        Long cocktailId = 12782L;
        Cocktail cocktail = cocktailService.findCocktailById(cocktailId);
        Assertions.assertThat(cocktail.getEngName()).isEqualTo("Thai Coffee");
        List<CocktailIngredient> cocktailIngredients = cocktail.getCocktailIngredients();
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            Ingredient ingredient = cocktailIngredient.getIngredient();
            System.out.println(ingredient.getEngName());
        }
    }

    @Test
    public void findCocktailDTOsTest() throws JsonProcessingException {
        List<CocktailDTO> cocktailDTOs = cocktailService.findAllCocktailDTOs();
        ObjectMapper objectMapper = new ObjectMapper();
        for (CocktailDTO cocktailDTO : cocktailDTOs) {
            System.out.println(objectMapper.writeValueAsString(cocktailDTO));
        }
    }
}
