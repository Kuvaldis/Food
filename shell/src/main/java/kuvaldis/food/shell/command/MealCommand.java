package kuvaldis.food.shell.command;

import kuvaldis.food.core.domain.MealCalculationParams;
import kuvaldis.food.core.domain.MealType;
import kuvaldis.food.core.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MealCommand implements CommandMarker {

    @Autowired
    private MealService mealService;

    @CliCommand(value = "meal calculate")
    public void test() {
        log.info("bla");
        mealService.calculate(new MealCalculationParams(MealType.BREAKFAST));
    }
}
