package com.theironyard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theironyard.exceptions.CourseFullException;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jeff on 8/8/16.
 */
@Table(name = "courses")
public class Course{

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Professor professor;

    @ManyToMany
    @JsonIgnore
    private Collection<Student> students;

    @Column(nullable = false)
    @ColumnDefault("5")
    private int maxStudents;


    public Course(String title, Professor professor) {
        this.title = title;
        this.professor = professor;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isFull(){
        return students.size() >= maxStudents;
    }

    public void addStudent(Student student){
        if(isFull()){
            students.add(student);
        }
        else{
            throw new CourseFullException();
        }
    }

    public void kickStudent(Student student){
        students.remove(student);
    }

    @Override
    public String toString() {
        return String.format("Course %s taught by %s", title, professor.getName());
    }
}
