package com.example.demo.dao;

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
    CommandLineRunner commandLineRunner(StudentRepository repository){
    return  args -> { Student John=new Student("John","john@example.com",LocalDate.of(2000,07,17),21);
        Student Alex=new Student("Alex","alex@example.com",LocalDate.of(2000,04,12),21);


        repository.saveAll(
            Arrays.asList(John,Alex));

    };

    }
}
