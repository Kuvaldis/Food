package kuvaldis.food.core.service;

import kuvaldis.food.core.domain.CourseType;
import kuvaldis.food.core.domain.MealType;
import kuvaldis.food.core.domain.MealsConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class MealsConfigurationServiceImplTest {

    private MealsConfigurationService mealsConfigurationService;

    @Before
    public void setUp() throws Exception {
        mealsConfigurationService = new MealsConfigurationServiceImpl();
    }

    @Test
    public void testGetShouldReturnNotNullWithNotEmptyConfigForEachMealType() throws Exception {
        // when
        final MealsConfiguration configuration = mealsConfigurationService.get();
        // then
        assertThat(configuration).isNotNull();
        Arrays.asList(MealType.values()).stream()
                .forEach(mealType -> {
                    List<CourseType> courses = configuration.forMealType(mealType);
                    assertThat(courses).isNotNull();
                    assertThat(courses).isNotEmpty();
                });
    }
}