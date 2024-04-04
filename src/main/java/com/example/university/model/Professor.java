package com.example.university.model;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int professorId;
    @Column(name = "name")
    private String professorName;
    @Column(name = "department")
    private String department;

    public Professor() {
    }

    public Professor(int id, String name, String department) {
        this.professorId = id;
        this.professorName = name;
        this.department = department;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int id) {
        this.professorId = id;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String name) {
        this.professorName = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}