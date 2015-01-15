package kuvaldis.food.core.domain;

import lombok.Data;

@Data
public class CourseIngredient {
    private final Ingredient ingredient;
    private final Volume volume;
}
