package com.example.university.repository;

import java.util.ArrayList;
import com.example.university.model.Professor;
import com.example.university.model.Course;

public interface ProfessorRepository {

    ArrayList<Professor> getProfessors();

    Professor getProfessorById(int professorId);

    Professor addProfessor(Professor professor);

    Professor updateProfessor(int professorId, Professor professor);

    void deleteProfessor(int professorId);

    ArrayList<Course> getProfessorCourses(int professorId);
}