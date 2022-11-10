package com.example.demo.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.model.Faculty;
import com.example.demo.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FacultyController.class})
@ExtendWith(SpringExtension.class)
class FacultyControllerTest {
    @Autowired
    private FacultyController facultyController;

    @MockBean
    private FacultyService facultyService;


    @Test
    void testGetFaculty() throws Exception {
        when(facultyService.getFaculty()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/faculty");
        MockMvcBuilders.standaloneSetup(facultyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testDeleteFaculty() throws Exception {
        doNothing().when(facultyService).deleteFaculty((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/faculty/{facultyId}", 123L);
        MockMvcBuilders.standaloneSetup(facultyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testUpdateFaculty() throws Exception {
        doNothing().when(facultyService).updateFaculty((Long) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/faculty/{facultyId}", 123L);
        MockMvcBuilders.standaloneSetup(facultyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FacultyController#updateFaculty(Long, String, String)}
     */
    @Test
    void testUpdateFaculty2() throws Exception {
        doNothing().when(facultyService).updateFaculty((Long) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/v1/faculty/{facultyId}", 123L);
        putResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(facultyController)
                .build()
                .perform(putResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testRegisterNewFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setAge(1);
        faculty.setDob(null);
        faculty.setEmail("jane.doe@example.org");
        faculty.setId(123L);

        faculty.setName("Name");

        faculty.setSubject("Physics");
        String content = (new ObjectMapper()).writeValueAsString(faculty);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(facultyController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }


}

