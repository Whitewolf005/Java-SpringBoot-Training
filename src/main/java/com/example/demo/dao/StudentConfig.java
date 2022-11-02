package com.example.demo.dao;

import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class StudentConfig
{
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository, FacultyRepository repository1){
     //   System.out.println("Helllloooo.............................  Student");



    return  args -> { Student John=new Student("John","john@example.com",LocalDate.of(2000,07,17),21);
        Student Alex=new Student("Alex","alex@example.com",LocalDate.of(2000,04,12),21);
        Faculty Smith = new Faculty("Smith", "smith@example.com", LocalDate.of(1900, 4, 21), 31, "English");
        Faculty Anderson=new Faculty("Anderson","anderson@example.com",LocalDate.of(1900,8,19),31,"Mathematics");


        repository.saveAll(
            Arrays.asList(John,Alex));

        repository1.saveAll(
                Arrays.asList(Smith,Anderson));

    };

    }
}
