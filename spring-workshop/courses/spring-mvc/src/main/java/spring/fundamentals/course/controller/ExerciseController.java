package spring.fundamentals.course.controller;

import spring.fundamentals.course.model.Course;
import spring.fundamentals.course.service.DataService;

public class ExerciseController {
    private final DataService dataService;

    public ExerciseController(DataService dataService) {
        this.dataService = dataService;
    }

    public String saveCourse(Course course) {
        dataService.saveCourse(course);
        return "";
    }

    public String deleteCourse(int id) {
        dataService.removeCourseById(id);
        return "";
    }
}
