package kuvaldis.food.core.service;

import kuvaldis.food.core.domain.Meal;
import kuvaldis.food.core.domain.MealCalculationParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MealServiceImpl implements MealService {

    @Value("${some}")
    private String value;

    @Override
    public Meal calculate(MealCalculationParams params) {
        System.out.println(value);
        log.debug("calculate {}", params);
        return null;
    }
}
