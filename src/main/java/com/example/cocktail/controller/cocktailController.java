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

import java.util.List;

@Slf4j
@Controller
public class cocktailController {

    @Autowired
    CocktailService cocktailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/cocktail/save")
    public String cocktailSave(@RequestBody CocktailDTO cocktailDTO) {
        cocktailService.save(cocktailDTO);
        return "cocktailSave";
    }

    @PostMapping("/cocktail/save/multiple")
    public String cocktailSaveMultiple(@RequestBody String messageBody) throws JsonProcessingException {
        List<CocktailDTO> cocktailDTOs
                = objectMapper.readValue(messageBody, new TypeReference<>() {});
        for (CocktailDTO cocktailDTO : cocktailDTOs) {
            log.info("id = " + cocktailDTO.getId());
            cocktailService.save(cocktailDTO);
        }
        return "cocktailSave";
    }
}