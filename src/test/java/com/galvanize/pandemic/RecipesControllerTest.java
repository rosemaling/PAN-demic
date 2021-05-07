package com.galvanize.pandemic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        for(int i = 0; i < 10; i++) {
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

}
