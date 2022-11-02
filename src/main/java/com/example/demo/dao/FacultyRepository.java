package com.example.demo.dao;

import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>
{
    Optional<Faculty> findFacultyByEmail(String Email);
}
