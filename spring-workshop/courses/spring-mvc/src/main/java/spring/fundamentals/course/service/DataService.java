package spring.fundamentals.course.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import spring.fundamentals.course.model.Course;
import spring.fundamentals.course.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private List<Course> cachedData;
    private int lastGeneratedCourseId;
    private int lastGeneratedStudentId;

    @PostConstruct
    private void init() {
        Student student1 = new Student(1, "John Doe", 20);
        Student student2 = new Student(2, "Jane Smith", 22);
        Student student3 = new Student(3, "Mike Johnson", 21);

        // Sample courses
        Course course1 = new Course(1, "Java 101", "Introduction to Java programming.", "Popescu Marcel", Arrays.asList(student1, student2, student3));
        Course course2 = new Course(2, "Spring Boot 101", "Learn the basics of Spring Boot.", "Ionescu Diana", Arrays.asList(student1, student3));

        // Add the courses to the model
        cachedData = Arrays.asList(course1, course2);
        lastGeneratedCourseId = 2;
        lastGeneratedStudentId = 3;
    }

    public void saveCourse(Course course) {
        if (course.getId() == 0) {
            lastGeneratedCourseId += 1;
            course.setId(lastGeneratedCourseId);
        }
        List<Course> newList = new ArrayList<>(cachedData);
        newList.add(course);
        cachedData = newList.stream().toList();
    }

    public void removeCourseById(int id) {
        List<Course> newCourses = cachedData.stream().filter(course -> course.getId() != id).collect(Collectors.toList());
        if (newCourses.size() == cachedData.size()) {
            throw new RuntimeException("remove failed. no available course id");
        }
        cachedData = newCourses;
    }

    public List<Course> getAllCourses() {
        return cachedData;
    }

    public void unenrollStudent(int courseId, int studentId) {
        Course course = cachedData.stream()
                .filter(c -> c.getId() == courseId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("missing course id"));

        List<Student> newList = course.getStudents().stream()
                .filter(student -> student.getId() != studentId)
                .toList();
        if (newList.size() == course.getStudents().size()) {
            throw new RuntimeException("student id invalid");
        }
        course.setStudents(newList);
    }

    public void enrollStudent(int courseId, Student student) {
        Course course = cachedData.stream()
                .filter(c -> c.getId() == courseId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("missing course id"));

        List<Student> students = course.getStudents() != null ? new ArrayList<>(course.getStudents()) : new ArrayList<>();
        if (student.getId() == 0) {
            lastGeneratedStudentId += 1;
            student.setId(lastGeneratedStudentId);
        }
        students.add(student);
        course.setStudents(students.stream().toList());
    }

}
