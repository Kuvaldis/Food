package kuvaldis.food.domain;

import lombok.Data;

@Data
public class CourseIngredient {
    private Ingredient ingredient;
    private Volume volume;
}
