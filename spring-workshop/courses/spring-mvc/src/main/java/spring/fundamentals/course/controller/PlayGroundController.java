package spring.fundamentals.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.fundamentals.course.model.Course;
import spring.fundamentals.course.service.DataService;

import java.util.List;


@Controller
public class PlayGroundController {

    private final DataService dataService;

    public PlayGroundController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public String index() {
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String coursesPage(Model model) {
        List<Course> courses = dataService.getAllCourses();

        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/courses/{courseId}/students/{studentId}/unenroll")
    public String unenrollStudent(@PathVariable int courseId, @PathVariable int studentId) {
        dataService.unenrollStudent(courseId, studentId);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{courseId}/remove")
    public String removeCourse(@PathVariable int courseId) {
        dataService.removeCourseById(courseId);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/students/enroll")
    public String navigateToEnrollStudents(Model model, @PathVariable int courseId) {
        model.addAttribute("course.id", courseId);
        return "enroll-students";
    }
}
