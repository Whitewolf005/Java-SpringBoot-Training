package com.example.demo.dao;


import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class FacultyConfig
{
    @Bean
    CommandLineRunner commandLineRunner(FacultyRepository repository){
        return  args -> { Faculty Smith=new Faculty("Smith","smith@example.com", LocalDate.of(1900,4,21),31,"English");
            Faculty Anderson=new Faculty("Anderson","anderson@example.com",LocalDate.of(1900,8,19),31,"Mathematics");


            repository.saveAll(
                    Arrays.asList(Smith,Anderson));

        };

    }
}
