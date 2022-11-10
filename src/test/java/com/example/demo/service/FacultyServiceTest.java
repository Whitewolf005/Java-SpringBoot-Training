package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.dao.FacultyRepository;
import com.example.demo.model.Faculty;
import com.example.demo.model.Result;
import com.example.demo.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceTest {
    @MockBean
    private FacultyRepository facultyRepository;

    @Autowired
    private FacultyService facultyService;


    @Test
    void testAddNewFaculty() {
        Faculty faculty = new Faculty();
        faculty.setAge(1);
        faculty.setDob(LocalDate.ofEpochDay(1L));
        faculty.setEmail("jane.doe@example.org");
        faculty.setId(123L);

        faculty.setName("Name");

        faculty.setSubject("English");

        Faculty faculty1 = new Faculty();
        faculty1.setAge(1);
        faculty1.setDob(LocalDate.ofEpochDay(1L));
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setId(123L);

        faculty1.setName("Name");
        
        faculty1.setSubject("physics");
        Optional<Faculty> ofResult = Optional.of(faculty1);
        when(facultyRepository.save((Faculty) any())).thenReturn(faculty);
        when(facultyRepository.findByEmail((String) any())).thenReturn(ofResult);


    }


    @Test
    void testGetFaculty() {
        ArrayList<Faculty> facultyList = new ArrayList<>();
        when(facultyRepository.findAll()).thenReturn(facultyList);
        List<Faculty> actualFaculty = facultyService.getFaculty();
        assertSame(facultyList, actualFaculty);
        assertTrue(actualFaculty.isEmpty());
        verify(facultyRepository).findAll();
    }


    @Test
    void testDeleteFaculty() {
        doNothing().when(facultyRepository).deleteById((Long) any());
        when(facultyRepository.existsById((Long) any())).thenReturn(true);
        facultyService.deleteFaculty(123L);
        verify(facultyRepository).existsById((Long) any());
        verify(facultyRepository).deleteById((Long) any());
        assertTrue(facultyService.getFaculty().isEmpty());
    }


    @Test
    void testUpdateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setAge(1);
        faculty.setDob(LocalDate.ofEpochDay(1L));
        faculty.setEmail("jane.doe@example.org");
        faculty.setId(123L);

        faculty.setName("Name");

        faculty.setSubject("Physics");
        Optional<Faculty> ofResult = Optional.of(faculty);
        when(facultyRepository.findById((Long) any())).thenReturn(ofResult);
        facultyService.updateFaculty(123L, "Name", "jane.doe@example.org");
        verify(facultyRepository).findById((Long) any());
    }




}

