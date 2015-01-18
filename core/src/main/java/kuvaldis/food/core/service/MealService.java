package kuvaldis.food.core.service;

import kuvaldis.food.core.domain.Meal;
import kuvaldis.food.core.domain.MealCalculationParams;

public interface MealService {
    Meal calculate(MealCalculationParams params);
}
