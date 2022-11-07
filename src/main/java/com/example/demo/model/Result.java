package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Result
{

    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName="student_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "student_sequence"

    )



    private long id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @JoinColumn(name="Stu_ID")
           // @JoinColumn(name="ADDR", referencedColumnName="ZIP")

    private Student student;
    //private long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="Fac_ID")

    private Faculty faculty;



    private String name;

    private String subject;

    private int marks;


    public Result() {

    }

    public Result( String name, String subject, int marks) {
        //this.id = id;
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Result{" +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", marks=" + marks +
                '}';
    }
}
