package kuvaldis.food.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Meal {
    private final MealType mealType;
    private final List<Course> courses;
}
