package kuvaldis.food.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Meal {
    private MealType mealType;
    private List<Course> courses;
}
