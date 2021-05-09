package com.galvanize.pandemic;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return dataService.getRecipe(id);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return dataService.addRecipe(recipe);
    }

    @PatchMapping("/{id}")
    public Recipe updateRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        return dataService.updateRecipe(id, recipe);
    }
}
