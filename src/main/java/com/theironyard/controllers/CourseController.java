package com.theironyard.controllers;

import com.sun.org.apache.regexp.internal.RE;
import com.theironyard.entities.Course;
import com.theironyard.entities.Professor;
import com.theironyard.entities.Student;
import com.theironyard.exceptions.NotClassOwnerException;
import com.theironyard.services.CourseRepository;
import com.theironyard.services.ProfessorRepository;
import com.theironyard.services.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jeff on 8/8/16.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Course createCourse(@RequestHeader("Authorization") String auth, @RequestBody Course course){
        Professor professor = professorRepository.getOne(Integer.parseInt(auth.split(" ")[1]));

        course.setProfessor(professor);
        courseRepository.save(course);
        return course;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable int id){
        return courseRepository.getOne(id);
    }

    @RequestMapping(path = "/{id}/students/{studentId}", method = RequestMethod.DELETE)
    public void dropStudent(@RequestHeader("Authorization") String auth,
                            @PathVariable int id, @PathVariable int studentId){
        Professor professor = professorRepository.getOne(Integer.parseInt(auth.split(" ")[1]));
        Course course = courseRepository.getOne(id);

        if(professor.getId() != course.getProfessor().getId()){
            throw new NotClassOwnerException();
        }

        Student student = studentRepository.getOne(studentId);
        course.kickStudent(student);
    }
}
