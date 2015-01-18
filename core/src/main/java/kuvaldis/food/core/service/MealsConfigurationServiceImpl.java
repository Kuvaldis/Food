package kuvaldis.food.core.service;

import kuvaldis.food.core.domain.CourseType;
import kuvaldis.food.core.domain.MealType;
import kuvaldis.food.core.domain.MealsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class MealsConfigurationServiceImpl implements MealsConfigurationService {
    @Override
    public MealsConfiguration get() {
        if (log.isDebugEnabled()) {
            log.debug("get");
        }
        return getDefault();
    }

    public static MealsConfiguration getDefault() {
        return new MealsConfiguration(new HashMap<MealType, List<CourseType>>() {{
            put(MealType.BREAKFAST, Arrays.asList(CourseType.MAIN, CourseType.DRINK, CourseType.DESSERT));
            put(MealType.SECOND_BREAKFAST, Arrays.asList(CourseType.APPETIZER, CourseType.DRINK));
            put(MealType.LUNCH, Arrays.asList(CourseType.values()));
            put(MealType.TEA, Arrays.asList(CourseType.DRINK, CourseType.SNACK, CourseType.DESSERT));
            put(MealType.DINNER, Arrays.asList(CourseType.values()));
            put(MealType.MIDNIGHT_SNACK, Arrays.asList(CourseType.DRINK, CourseType.SNACK, CourseType.APPETIZER));
        }});
    }

}
