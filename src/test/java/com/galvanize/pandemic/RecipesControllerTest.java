package com.galvanize.pandemic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class RecipesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataService dataService;

    List<Recipe> recipeList;

    @BeforeEach
    void setUp() {
        recipeList = new ArrayList<Recipe>();
        for (int i = 0; i < 10; i++) {
            recipeList.add(new Recipe("taquitos-" + i, "this is how to make it", "this is what you need"));
        }
    }

    @Test
    void getRecipes() throws Exception {
        when(dataService.getRecipes()).thenReturn(recipeList);

        mockMvc.perform(get("/recipes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void getRecipeById() throws Exception {
        Recipe expected = new Recipe("sandwich", "you put the ingredients between the bread idiot", "bread and stuff");
        when(dataService.getRecipe(anyInt())).thenReturn(expected);

        mockMvc.perform(get("/recipes/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("sandwich"))
                .andExpect(jsonPath("directions").value("you put the ingredients between the bread idiot"))
                .andExpect(jsonPath("ingredients").value("bread and stuff"));
    }

    @Test
    void addRecipe() throws Exception {
        when(dataService.addRecipe(any(Recipe.class))).thenReturn(new Recipe("sandwich", "you put the ingredients between the bread idiot", "bread and stuff"));

        mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"sandwich\", \"directions\":\"you put the ingredients between the bread idiot\", \"ingredients\":\"bread and stuff\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("name").value("sandwich"))
                        .andExpect(jsonPath("directions").value("you put the ingredients between the bread idiot"))
                        .andExpect(jsonPath("ingredients").value("bread and stuff"));
    }

    @Test
    void updateRecipeById() throws Exception {
        Recipe updated = new Recipe("sandwich", "you put the ham between the bread", "bread and ham");
        when(dataService.updateRecipe(anyInt(), any(Recipe.class))).thenReturn(updated);

        mockMvc.perform(patch("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"sandwich\", \"directions\":\"you put the ham between the bread\", \"ingredients\":\"bread and ham\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("name").value("sandwich"))
                        .andExpect(jsonPath("directions").value("you put the ham between the bread"))
                        .andExpect(jsonPath("ingredients").value("bread and ham"));
    }
}
