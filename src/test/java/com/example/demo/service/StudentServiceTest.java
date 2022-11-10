package com.example.demo.service;

import antlr.build.Tool;
import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Faculty;
import com.example.demo.model.Result;
import com.example.demo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    // @InjectMocks


    private Student s;
    private Student s1;
    private final List<Student> students = new ArrayList<>();

    @BeforeEach
    void setUp() {

        studentService = new StudentService(studentRepository);
        s = new Student();
        s.setId(1);
        s.setName("John");
        s.setEmail("john@example.com");
        s.setDob(LocalDate.of(2000, 07, 17));
        s.setAge(21);

        s1 = new Student();
        s1.setId(2);
        s1.setName("Alex");
        s1.setEmail("alex@example.com");
        s1.setDob(LocalDate.of(2000, 05, 18));
        s1.setAge(21);

        students.add(s);
        students.add(s1);

    }



    @Test
    void getStudents() throws Exception {


        when(studentRepository.findAll()).thenReturn(students);
        List<Student> foundStudents = studentService.getStudents();
        assertNotNull(foundStudents);
        assertEquals(2, studentService.getStudents().spliterator().estimateSize());
        assertEquals("com.example.demo.model.Student", studentService.getStudents().iterator().next().getClass().getName());


    }


    @Test
    void addNewStudent() throws Exception {

        when(studentRepository.findAll()).thenReturn(students);


        // when(studentRepository.findStudentByEmail(s1.getEmail())).thenReturn(true);
        assertTrue(studentService.addNewStudent(s));
        assertTrue(studentService.addNewStudent(s1));
        List<Student> foundStudents = studentService.getStudents();
        assertNotNull(foundStudents);
        System.out.println(foundStudents.size());


    }


    @Test
    void deleteStudent() {




        when(studentRepository.existsById((Long) any())).thenReturn(true);
        studentService.deleteStudent(s.getId());

        Mockito.verify(studentRepository, Mockito.times(1))
                .deleteById(s.getId());

    }


    @Test
    void testDeleteStudent() {
        Mockito.when(studentRepository.findAll()).thenReturn(students);

        doNothing().when(studentRepository).deleteById(s.getId());
        when(studentRepository.existsById((Long) any())).thenReturn(true);
        assertTrue(studentService.deleteStudent(s.getId()));
        verify(studentRepository, atLeast(1)).existsById(s.getId());
        verify(studentRepository).deleteById(s.getId());
    }


    @Test
    void testUpdateStudent() {
//
        Optional<Student> ofResult = Optional.of(s);
        when(studentRepository.findById((Long) s.getId())).thenReturn(ofResult);
        studentService.updateStudent(s.getId(), "Name", "jane@example.org");
        verify(studentRepository).findById((Long) s.getId());
    }




    @Test
    void updateStudent() {

    }
}