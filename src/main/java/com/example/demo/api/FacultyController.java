package com.example.demo.api;

import com.example.demo.model.Faculty;

import com.example.demo.service.FacultyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
@RestController
public class FacultyController
{
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping(path = "api/v1/faculty")
    public List<Faculty> getFaculty() {
        return facultyService.getFaculty();
    }

    @PostMapping(path = "api/v1/faculty")
    public void registerNewFaculty(@RequestBody Faculty faculty) {
        FacultyService.addNewFaculty(faculty);
    }

    @DeleteMapping(path="{facultyId}")
    public void deleteFaculty(@PathVariable("facultyId") Long Id)
    {
        facultyService.deleteFaculty(Id);
    }

    @PutMapping(path="api/v1/faculty/{facultyId}")
    public void updateFaculty(
            @PathVariable("facultyId") Long Id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email)
    {
        facultyService.updateFaculty(Id,name,email);
    }
}
