package com.example.university.model;

import com.example.university.model.Professor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int courseId;
    @Column(name = "name")
    private String courseName;
    @Column(name = "credits")
    private String credits;

    @ManyToOne
    @JoinColumn(name = "professorid")
    private Professor professor;

    @ManyToMany
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "courseid"), inverseJoinColumns = @JoinColumn(name = "studentid"))
    @JsonIgnoreProperties("courses")
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(int id, String name, String credits, Professor professor, List<Student> students) {
        this.courseId = id;
        this.courseName = name;
        this.credits = credits;
        this.professor = professor;
        this.students = students;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int id) {
        this.courseId = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
