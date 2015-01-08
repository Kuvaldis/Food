package kuvaldis.food.domain;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private List<Ingredient> ingredients;
}
