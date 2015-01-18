package kuvaldis.food.shell.command;

import com.brsanthu.dataexporter.model.AlignType;
import com.brsanthu.dataexporter.model.LineNumberColumn;
import com.brsanthu.dataexporter.model.StringColumn;
import com.brsanthu.dataexporter.output.text.TextExporter;
import com.brsanthu.dataexporter.output.texttable.TextTableExporter;
import kuvaldis.food.core.domain.Meal;
import kuvaldis.food.core.domain.MealCalculationParams;
import kuvaldis.food.core.domain.MealType;
import kuvaldis.food.core.service.MealService;
import kuvaldis.food.fatsecret.service.FatSecretApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Slf4j
@Component
public class MealCommand extends AbstractShellCommandMarker {

    @Autowired
    private MealService mealService;

    @Autowired
    private FatSecretApiService fatSecretApiService;

    @CliCommand(value = "calculate meal", help = "calculate meal --type BREAKFAST")
    public void calculateMeal(@CliOption(key = "type", mandatory = true) final MealType mealType) {
        final Meal meal = mealService.calculate(new MealCalculationParams(mealType));
        handsomePrint(meal);
    }

    @CliCommand(value = "test")
    public void test() {
        System.out.println(fatSecretApiService.getFood(4384l));
    }

    private void handsomePrint(Meal meal) {
        final StringWriter sw = new StringWriter();
        sw.append(String.format("%n"));
        final TextExporter headerExporter = new TextExporter(sw);
        headerExporter.addColumns(new StringColumn("Courses for meal type: "), new StringColumn(meal.getMealType().toString()));
        headerExporter.startExporting();
        headerExporter.finishExporting();
        sw.append(String.format("%n"));
        final TextTableExporter coursesExporter = new TextTableExporter(sw);
        coursesExporter.addColumns(new LineNumberColumn("Line No", 5), new StringColumn("Course name", 20, AlignType.MIDDLE_CENTER));
        coursesExporter.startExporting();
        meal.getCourses().stream().forEach(course -> coursesExporter.addRow(course.getName()));
        coursesExporter.finishExporting();
        log.info(sw.toString());
    }
}