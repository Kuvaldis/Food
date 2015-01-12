package kuvaldis.food.core.service;

import kuvaldis.food.core.domain.Meal;
import kuvaldis.food.core.domain.MealCalculationParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MealServiceImpl implements MealService {
    @Override
    public Meal calculate(MealCalculationParams params) {
        log.debug("calculate {}", params);
        return null;
    }
}
