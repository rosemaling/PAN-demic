package com.galvanize.pandemic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataService {
    private static int nextId = 0;
    private List<Recipe> recipeList;

    public static int getNextId() {
        int id = nextId;
        nextId++;
        return id;
    }

    public List<Recipe> getRecipes() {
        return recipeList;
    }

    public Recipe getRecipe(int id) {
        return null;
    }

    public Recipe addRecipe(Recipe recipe) {
        recipe.setId(getNextId());
        recipeList.add(recipe);
        return recipe;
    }
}
