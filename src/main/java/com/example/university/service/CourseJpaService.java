package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.university.model.*;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.ProfessorJpaRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.model.Course;

import com.example.university.model.Professor;

@Service
public class CourseJpaService implements CourseRepository {

    @Autowired
    public CourseJpaRepository courseJpaRepository;

    @Autowired
    public ProfessorJpaRepository professorJpaRepository;

    @Autowired
    public StudentJpaRepository studentJpaRepository;

    @Override
    public ArrayList<Course> getCourses() {
        List<Course> courseList = courseJpaRepository.findAll();
        ArrayList<Course> courses = new ArrayList<>(courseList);
        return courses;
    }

    @Override
    public Course getCourseById(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            return course;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Course addCourse(Course course) {
        try {
            Professor professor = course.getProfessor();
            int professorId = professor.getProfessorId();
            Professor newProfessor = professorJpaRepository.findById(professorId).get();
            course.setProfessor(newProfessor);

            List<Integer> studentIds = new ArrayList<>();
            for (Student student : course.getStudents()) {
                studentIds.add(student.getStudentId());
            }

            List<Student> students = studentJpaRepository.findAllById(studentIds);
            if (studentIds.size() != students.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "std id is worng");
            }
            course.setStudents(students);
            return courseJpaRepository.save(course);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong profId");
        }
    }

    @Override
    public Course updateCourse(int courseId, Course course) {
        try {
            Course newCourse = courseJpaRepository.findById(courseId).get();
            if (course.getCourseName() != null) {
                newCourse.setCourseName(course.getCourseName());
            }
            if (course.getCredits() != null) {
                newCourse.setCredits(course.getCredits());
            }
            if (course.getProfessor() != null) {
                Professor professor = course.getProfessor();
                int professorId = professor.getProfessorId();
                Professor newProfessor = professorJpaRepository.findById(professorId).get();
                newCourse.setProfessor(newProfessor);
                professorJpaRepository.save(newProfessor);
            }
            if (course.getStudents() != null) {
                List<Integer> studentIds = new ArrayList<>();

                for (Student student : course.getStudents()) {
                    studentIds.add(student.getStudentId());
                }
                List<Student> students = studentJpaRepository.findAllById(studentIds);
                if (students.size() != studentIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong studentid");
                }
                newCourse.setStudents(students);
            }
            courseJpaRepository.save(newCourse);
            return newCourse;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong courseId");
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            List<Student> students = course.getStudents();
            for (Student student : students) {
                student.getCourses().remove(course);
            }
            studentJpaRepository.saveAll(students);
            courseJpaRepository.deleteById(courseId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Professor getCourseProfessor(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            Professor professor = course.getProfessor();
            return professor;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Student> getCourseStudents(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            List<Student> students = course.getStudents();
            return students;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}