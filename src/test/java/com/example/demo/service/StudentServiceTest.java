package com.example.demo.service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.Optional;

import static com.example.demo.service.StudentService.studentRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStudents()
    {
        Student s=  new Student(1,"John","john@example.com", LocalDate.of(2000,07,17),21);
        Student s1=  new Student(2,"Alex","alex@example.com", LocalDate.of(2000,05,18),21);

        List<Student> sl= Arrays.asList(s,s1);
        when(studentRepository.findAll()).thenReturn(sl);
        assertEquals(2 , studentService.getStudents().spliterator().estimateSize()) ;
        assertEquals("com.example.demo.model.Student" , studentService.getStudents().iterator().next().getClass().getName());

    }


    @Test
    void addNewStudent()
    {
        Student s=  new Student("John","john@example.com", LocalDate.of(2000,07,17),21);

        when(studentRepository.save(s)).thenReturn(s);

       // assertEquals(1 , studentService.addNewStudent(s).getClass().getId()) ;
        assertTrue(studentService.addNewStudent(s));


    }

    @Test
     void deleteStudent()
    {
        Optional<Student> s= Optional.of(new Student(1, "John", "john@example.com", LocalDate.of(2000, 07, 17), 21));
        // studentRepository.save(s);
        //List<Student> sl= Arrays.asList(s);
        when(studentRepository.findById(1L)).thenReturn(s);
        when(studentRepository.save(s)).thenReturn(Optional.of(s));

       // when(studentRepository.save(s)).thenReturn(s);
        //System.out.println(studentRepository.findAll());


        Optional<Student> s1=s;
        assertTrue(studentService.deleteStudent(1l));
        org.assertj.core.api.Assertions.assertThat(studentRepository.findById(1L)).isNull();
        //assertEquals(true , studentService.deleteStudent(1l)) ;
      //  assertEquals("John" , studentService.deleteStudent(s.getId()).getClass().getName());
       // assertTrue( (studentService.deleteStudent(1L).isPresent()));

    }

    @Test
    void updateStudent()
    {

    }
}