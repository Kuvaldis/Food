package kuvaldis.food.core.service;

import kuvaldis.food.core.persistence.CourseRepository;
import kuvaldis.food.core.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealServiceImpl implements MealService {

    @Autowired
    private MealsConfigurationService mealsConfigurationService;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Meal calculate(MealCalculationParams params) {
        if (log.isDebugEnabled()) {
            log.debug("calculate {}", params);
        }
        if (params == null || params.getMealType() == null) {
            return null;
        }
        MealType mealType = params.getMealType();
        final MealsConfiguration configuration = mealsConfigurationService.get();
        final List<CourseType> possibleTypes = configuration.forMealType(mealType);
        final List<Course> courses = courseRepository.get(mealType);
        final List<Course> calculatedCourses = possibleTypes.stream()
                .map(type -> findAppropriate(type, courses))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new Meal(mealType, calculatedCourses);
    }

    private Course findAppropriate(final CourseType mealType, final List<Course> courses) {
        final List<Course> filtered = courses.stream()
                .filter(course -> course.getCourseType().equals(mealType))
                .collect(Collectors.toList());
        if (filtered.size() > 0) {
            final Random random = ThreadLocalRandom.current();
            return filtered.get(random.nextInt(filtered.size()));
        }
        return null;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void setMealsConfigurationService(MealsConfigurationService mealsConfigurationService) {
        this.mealsConfigurationService = mealsConfigurationService;
    }
}
