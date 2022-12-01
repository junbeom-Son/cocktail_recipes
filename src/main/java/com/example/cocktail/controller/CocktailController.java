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
        cocktailService.save(cocktailDTO);
        return 1;
    }

    @PostMapping("/save/multiple")
    public long cocktailSaveMultiple(@RequestBody List<CocktailDTO> cocktailDTOs) {
        long count = 0;
        for (CocktailDTO cocktailDTO : cocktailDTOs) {
            cocktailService.save(cocktailDTO);
            count++;
        }
        return count;
    }

    @GetMapping("/{cocktailID}")
    public CocktailDTO getCocktailById(@PathVariable Long cocktailID) {
        return cocktailService.findCocktailDTOById(cocktailID);
    }

    @GetMapping
    public List<CocktailDTO> getCocktailDTOs() {
        return cocktailService.findAllCocktailDTOs();
    }
}
