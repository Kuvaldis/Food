package kuvaldis.food.core.service;

import com.google.common.truth.Truth;
import kuvaldis.food.core.dao.CourseDao;
import kuvaldis.food.core.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceImplTest {

    private MealService mealService;

    @Mock
    private CourseDao courseDao;
    @Mock
    private MealsConfigurationService mealsConfigurationService;

    @Before
    public void setUp() throws Exception {
        final MealServiceImpl mealService = new MealServiceImpl();
        mealService.setCourseDao(courseDao);
        mealService.setMealsConfigurationService(mealsConfigurationService);
        this.mealService = mealService;
    }

    @Test
    public void testCalculateShouldReturnNullOnNullParam() throws Exception {
        // when
        final Meal meal = mealService.calculate(null);
        // then
        assertThat(meal).isNull();
    }

    @Test
    public void testCalculateShouldReturnNullIfMealTypeIsNull() throws Exception {
        // when
        final Meal meal = mealService.calculate(new MealCalculationParams(null));
        // then
        assertThat(meal).isNull();
    }

    @Test
    public void testCalculateShouldReturnNonEmptyValueAndNonEmptyCoursesAndCorrectTypeIfThereIsAppropriateCourses() throws Exception {
        // given
        final MealType mealType = MealType.BREAKFAST;
        final CourseType courseType = CourseType.MAIN;
        Course course1 = course("1", courseType, mealType);
        final List<Course> courses = Collections.singletonList(course1);
        when(courseDao.get(mealType)).thenReturn(courses);
        when(courseDao.get(courseType)).thenReturn(courses);
        when(mealsConfigurationService.get()).thenReturn(MealsConfigurationServiceImpl.getDefault());
        // when
        final Meal meal = mealService.calculate(new MealCalculationParams(mealType));
        // then
        assertThat(meal).isNotNull();
        assertThat(meal.getCourses()).isNotEmpty();
        assertThat(meal.getMealType()).isEqualTo(mealType);
    }

    private Course course(String courseName, CourseType courseType, MealType preferredMealType) {
        return new Course(courseName, courseType, Collections.singletonList(preferredMealType), null);
    }
}