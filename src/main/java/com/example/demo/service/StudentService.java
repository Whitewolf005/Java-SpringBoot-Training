package com.example.demo.service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private static StudentRepository studentRepository ;


    /*public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }*/



    public List<Student> getStudents() {


        List<Student> f=studentRepository.findAll();
        System.out.println("Hello...");
        for (Student s:f
             ) {
            System.out.println(s);
        }
        return f;
    }


    public static void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Already Taken..");
        }
        studentRepository.save(student);
    }



    public void deleteStudent(Long studentId) {
        boolean check = studentRepository.existsById(studentId);
        if (!check) {
            throw new IllegalStateException("Student with Id " + studentId + " doesn't exist");


        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email ) {
          Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with Id " + studentId + " doesn't exist"));



       if(name != null && name.length()>1 && !Objects.equals(student.getName(),name))
       {
           student.setName(name);
       }
        if(email != null && email.length()>1 && !Objects.equals(student.getEmail(),email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
            {
                throw new IllegalStateException("Email Already Taken");
            }
            student.setName(email);
        }
    }
}

