package com.example.demo.service;


import com.example.demo.api.FacultyController;
import com.example.demo.dao.FacultyRepository;

import com.example.demo.model.Faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FacultyService
{
    private static FacultyRepository facultyRepository = null;


    //FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void addNewFaculty(Faculty faculty) {
        Optional<Faculty> facultyOptional = facultyRepository.findByEmail(faculty.getEmail());

        if (facultyOptional.isPresent()) {
            throw new IllegalStateException("Email Already Taken..");
        }
        facultyRepository.save(faculty);
    }

    public List<Faculty> getFaculty() {

        return facultyRepository.findAll();
    }

    public void deleteFaculty(Long facultyId) {
        boolean check = facultyRepository.existsById(facultyId);
        if (!check) {
            throw new IllegalStateException("Faculty with Id " + facultyId + " doesn't exist");


        }
        facultyRepository.deleteById(facultyId);
    }

    @Transactional
    public void updateFaculty(Long facultyId, String name, String email ) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(()-> new IllegalStateException("Faculty with Id " + facultyId + " doesn't exist"));



        if(name != null && name.length()>1 && !Objects.equals(faculty.getName(),name))
        {
           faculty.setName(name);
        }
        if(email != null && email.length()>1 && !Objects.equals(faculty.getEmail(),email))
        {
            Optional<Faculty> facultyOptional = facultyRepository.findByEmail(email);
            if(facultyOptional.isPresent())
            {
                throw new IllegalStateException("Email Already Taken");
            }
               faculty.setName(email);
        }
    }
}
