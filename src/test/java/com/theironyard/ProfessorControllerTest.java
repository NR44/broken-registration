package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.Professor;
import com.theironyard.services.ProfessorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeff on 8/8/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfessorControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ProfessorRepository professorRepository;

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getProfessors() throws Exception {
        Professor p1 = new Professor("Winston");
        Professor p2 = new Professor("Howard");
        professorRepository.save(p1);
        professorRepository.save(p2);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/professors")).andReturn();

        ObjectMapper om = new ObjectMapper();
        List<Professor> professors = om.readValue(result.getResponse().getContentAsString(), ArrayList.class);

        assertEquals(2, professors.size());
    }
}
