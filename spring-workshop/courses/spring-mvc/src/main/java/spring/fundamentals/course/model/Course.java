package spring.fundamentals.course.model;

import java.util.List;

public class Course {

    private int id;
    private String name;
    private String description;
    private String trainer;
    private List<Student> students;

    public Course() {
    }

    public Course(int id, String name, String description, String trainer, List<Student> students) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.trainer = trainer;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", trainer=" + trainer +
                ", students=" + students +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
