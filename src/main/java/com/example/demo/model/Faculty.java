package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity

public class Faculty
{


    @Id
    @SequenceGenerator(
            name="faculty_sequence",
            sequenceName="faculty_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_sequence"

    )

    private long id;


    private String name;
    private String email;
    private LocalDate dob;
    private int age;
    private String subject;

    @JsonBackReference
    @ManyToMany()
    @JoinTable
            (
                    name="Student_like",
                    joinColumns = @JoinColumn(name = "faculty_id"),
                    inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student>  likedStudents = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "faculty")
    Set<Result> ress1;

    public Faculty() {

    }

    public Faculty(long id, String name, String email, LocalDate dob, int age, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.subject = subject;
    }

    public Faculty(String name, String email, LocalDate dob, int age, String subject) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public Set<Student> getLikedStudents() {
        return likedStudents;
    }

    public void setLikedStudents(Set<Student> likedStudents) {
        this.likedStudents = likedStudents;
    }

    public Set<Result> getRess1() {
        return ress1;
    }

    public void setRess1(Set<Result> ress1) {
        this.ress1 = ress1;
    }

   /* @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", subject='" + subject + '\'' +
                '}';
    }*/
}
