package com.example.demo.dao;

import com.example.demo.model.Faculty;
import com.example.demo.model.Result;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.*;

@Configuration
public class StudentConfig
{
    @Autowired
    StudentRepository repository;

    @Autowired
    FacultyRepository repository1;

    @Autowired
    ResultRepository repository2;
    @Bean
    CommandLineRunner commandLineRunner( ){
     //   System.out.println("Helllloooo.............................  Student");



    return  args -> { Student John=new Student("John","john@example.com",LocalDate.of(2000,07,17),21);
        Student Alex=new Student("Alex","alex@example.com",LocalDate.of(2000,04,12),21);
        Faculty Smith = new Faculty("Smith", "smith@example.com", LocalDate.of(1900, 4, 21), 31, "English");
        Faculty Anderson=new Faculty("Anderson","anderson@example.com",LocalDate.of(1900,8,19),31,"Mathematics");

        Result res= new Result("John","English",90);
        Result res1= new Result("Alex","Mathematics",95);


        repository.saveAll(
                Arrays.asList(John,Alex));

        repository1.saveAll(
                Arrays.asList(Smith,Anderson));

        repository2.saveAll(
                Arrays.asList(res,res1)
        );



        Set<Student> s = new HashSet();
        s.add(repository.findById(1l).get());
        s.add(repository.findById(2l).get());

        Smith.setLikedStudents(s);
        Anderson.setLikedStudents(s);

       /* Set<Student> s1=new HashSet<>();
        s1.add(repository.findById(1L).get());
        s1.add(repository.findById(2L).get());*/
        res.setStudent(John);
        res.setName(John.getName());
        res.setFaculty(Smith);

        res1.setStudent(John);
        res1.setName(John.getName());
        res1.setFaculty(Anderson);

        repository2.save(res);
        repository2.save(res1);

        repository1.save(Smith);
        repository1.save(Anderson);



    };

    }
}
