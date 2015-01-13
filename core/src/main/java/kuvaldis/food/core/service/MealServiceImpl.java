package kuvaldis.food.core.service;

import kuvaldis.food.core.domain.Meal;
import kuvaldis.food.core.domain.MealCalculationParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MealServiceImpl implements MealService {

    @Override
    public Meal calculate(MealCalculationParams params) {
        if (log.isDebugEnabled()) {
            log.debug("calculate {}", params);
        }
        return null;
    }
}
