package com.galvanize.pandemic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    DataService dataService;

    public RecipesController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<Recipe> getRecipes() {
        return dataService.getRecipes();
    }
}
