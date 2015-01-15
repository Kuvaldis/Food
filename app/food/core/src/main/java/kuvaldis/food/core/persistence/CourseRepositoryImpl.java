package kuvaldis.food.core.persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import kuvaldis.food.core.domain.Course;
import kuvaldis.food.core.domain.CourseType;
import kuvaldis.food.core.domain.MealType;
import kuvaldis.food.core.utils.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class CourseRepositoryImpl implements CourseRepository {

    @Value("${food.file.path}")
    private String foodFilePath;

    private Map<CourseType, List<Course>> courseTypeCache;
    private Map<MealType, List<Course>> preferredMealTypeCache;

    @PostConstruct
    public void init() {
        List<Course> readCourses = readFile();
        courseTypeCache = readCourses.stream()
                .collect(groupingBy(Course::getCourseType));
        preferredMealTypeCache = readCourses.stream()
                .flatMap(course -> course.getPreferredMealTypes().stream().map(type -> new Pair<>(type, course)))
                .collect(groupingBy(Pair::getFirst, mapping(Pair::getSecond, toList())));
    }

    private List<Course> readFile() {
        try (JsonReader reader =
                     new JsonReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(foodFilePath))))) {
            final List<Course> result = new ArrayList<>();
            final Gson gson = new Gson();
            reader.beginArray();
            while (reader.hasNext()) {
                result.add(gson.fromJson(reader, Course.class));
            }
            reader.endArray();
            return result;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> get(CourseType courseType) {
        return nullToEmpty(courseTypeCache.get(courseType));
    }

    @Override
    public List<Course> get(MealType preferredMealType) {
        return nullToEmpty(preferredMealTypeCache.get(preferredMealType));
    }

    private List<Course> nullToEmpty(List<Course> courses) {
        return Optional.ofNullable(courses).orElse(Collections.emptyList());
    }
}
