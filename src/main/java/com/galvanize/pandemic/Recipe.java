package com.galvanize.pandemic;

public class Recipe {
    private int id;
    private String name;
    private String directions;
    private String ingredients;

    public Recipe() {
    }

    public Recipe(String name, String directions, String ingredients) {
        //this.id = DataService.getNextId();
        this.name = name;
        this.directions = directions;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", directions='" + directions + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}
