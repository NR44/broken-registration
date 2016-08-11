package com.theironyard.controllers;

import com.theironyard.entities.Professor;
import com.theironyard.services.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jeff on 8/8/16.
 */
@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
    @Autowired
    ProfessorRepository professorRepository;

    @RequestMapping(path = "professors/", method = RequestMethod.GET)
    public List<Professor> getProfessors(){
        return professorRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Professor getProfessor(@PathVariable int id){
        return professorRepository.findOne(id);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Professor createProfessor(Professor professor){
        professorRepository.save(professor);
        return professor;
    }
}
