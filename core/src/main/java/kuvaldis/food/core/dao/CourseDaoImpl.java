package kuvaldis.food.core.dao;

import kuvaldis.food.core.domain.Course;
import kuvaldis.food.core.domain.CourseType;
import kuvaldis.food.core.domain.MealType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseDaoImpl implements CourseDao {
    @Override
    public List<Course> get(CourseType courseType) {
        return null;
    }

    @Override
    public List<Course> get(MealType preferredMealType) {
        return null;
    }
}
