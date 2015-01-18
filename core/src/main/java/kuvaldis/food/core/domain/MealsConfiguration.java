package kuvaldis.food.core.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Setter(value = AccessLevel.NONE)
public class MealsConfiguration {
    private final Map<MealType, List<CourseType>> mealCourses;

    public List<CourseType> forMealType(final MealType mealType) {
        return mealCourses.get(mealType);
    }
}
