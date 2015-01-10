package kuvaldis.food.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private List<CourseIngredient> ingredients;
}
