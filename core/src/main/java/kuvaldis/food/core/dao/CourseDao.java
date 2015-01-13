package kuvaldis.food.core.dao;

import kuvaldis.food.core.domain.Course;
import kuvaldis.food.core.domain.CourseType;
import kuvaldis.food.core.domain.MealType;

import java.util.List;

public interface CourseDao {
    List<Course> get(CourseType courseType);
    List<Course> get(MealType preferredMealType);
}
