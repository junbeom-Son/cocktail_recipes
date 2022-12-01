package com.example.cocktail.controller;

import com.example.cocktail.dto.CocktailDTO;
import com.example.cocktail.service.CocktailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cocktail")
public class CocktailController {

    @Autowired
    CocktailService cocktailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/save")
    public int cocktailSave(@RequestBody CocktailDTO cocktailDTO) {
        return cocktailService.save(cocktailDTO);
    }

    @PostMapping("/save/multiple")
    public int cocktailSaveMultiple(@RequestBody List<CocktailDTO> cocktailDTOs) {
        return cocktailService.saveCocktails(cocktailDTOs);
    }

    @GetMapping("/{cocktailID}")
    public CocktailDTO getCocktailById(@PathVariable Long cocktailID) {
        return cocktailService.findCocktailDTOById(cocktailID);
    }

    @GetMapping
    public List<CocktailDTO> getCocktailDTOs() {
        return cocktailService.findAllCocktailDTOs();
    }

    @PostMapping("/edit/{cocktailID}")
    public int editCocktail(@PathVariable Long cocktailID, CocktailDTO cocktailDTO) {
        return cocktailService.updateCocktail(cocktailID, cocktailDTO);
    }

    @PostMapping("/edit/multiple")
    public int editCocktails(List<CocktailDTO> cocktailDTOs) {
        return cocktailService.updateCocktails(cocktailDTOs);
    }
}
