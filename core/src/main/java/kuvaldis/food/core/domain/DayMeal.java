package kuvaldis.food.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class DayMeal {
    List<Meal> meals;
}
