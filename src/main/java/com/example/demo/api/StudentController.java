package com.example.demo.api;

import java.util.*;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping
public class StudentController {


    @Autowired
    private StudentService studentService;

    //@Autowired
    /*public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }*/

    @GetMapping(path = "api/v1/student")
    public List<Student> getStudent() {
        return studentService.getStudents();
    }

    @PostMapping(path = "api/v1/student")
    public void registerNewStudent(@RequestBody Student student) {
        StudentService.addNewStudent(student);
    }

    @DeleteMapping(path="student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long Id)
    {
       studentService.deleteStudent(Id);
    }

    @PutMapping(path="api/v1/student/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email)
    {
          studentService.updateStudent(Id,name,email);
    }
}
