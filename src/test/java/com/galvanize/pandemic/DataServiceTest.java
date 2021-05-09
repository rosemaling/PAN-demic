package com.galvanize.pandemic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataServiceTest {
    DataService dataService;

    List<Recipe> testData;

    @BeforeEach
    void setUp() {
        testData = new ArrayList<>();
        dataService = new DataService();
        for(int i = 0; i < 25; i++) {
            testData.add(dataService.addRecipe(new Recipe("sandwich" + i, "directions", "ingredients")));
        }
    }

    @Test
    void getRecipes() {
        assertEquals(25, dataService.getRecipes().size());
    }

    @Test
    void getRecipeById() {
        Recipe actual = testData.get(12);
        assertEquals(actual, dataService.getRecipe(12));
    }

    @Test
    void updateRecipe() {
        Recipe updatedRecipe = dataService.updateRecipe(1, new Recipe("cheese sandwich", "directions with cheese", "cheese"));

        assertEquals(dataService.getRecipe(1), updatedRecipe, "Should update the recipe at the given id");
    }
}
