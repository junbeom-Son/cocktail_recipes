package com.example.cocktail.controller;

import com.example.cocktail.dto.CocktailDTO;
import com.example.cocktail.service.CocktailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cocktail")
public class cocktailController {

    @Autowired
    CocktailService cocktailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/save")
    public String cocktailSave(@RequestBody CocktailDTO cocktailDTO) {
        cocktailService.save(cocktailDTO);
        return "cocktailSave";
    }

    @PostMapping("/save/multiple")
    public String cocktailSaveMultiple(@RequestBody List<CocktailDTO> cocktailDTOs) {
        for (CocktailDTO cocktailDTO : cocktailDTOs) {
            cocktailService.save(cocktailDTO);
        }
        return "cocktailSave";
    }
}
