package com.theironyard.controllers;

import com.theironyard.entities.Course;
import com.theironyard.entities.Student;
import com.theironyard.services.CourseRepository;
import com.theironyard.services.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jeff on 8/8/16.
 */
@RequestMapping(path = "/students")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable int id){
        return studentRepository.findOne(id);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Student createStudent(@RequestBody Student student){
        studentRepository.save(student);
        return student;
    }

    @RequestMapping(path = "/{id}/courses/{courseId}", method = RequestMethod.DELETE)
    public void deleteCourse(@RequestHeader String auth, @PathVariable int id, @PathVariable int courseId) throws Exception {
        Student student = studentRepository.getOne(Integer.parseInt(auth.split(" ")[1]));

        if(student.getId() != id){
            throw new Exception("You are not authorized to change that student's coursework");
        }

        Course course = courseRepository.getOne(courseId);
        student.dropCourse(course);
    }
}
