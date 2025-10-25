package org.example;

//public record Student(String name, Subject mainSubject){}

public class Student {
    private String name;
    private Subject mainSubject;

    public Student(String name, Subject mainSubject) {
        this.name = name;
        this.mainSubject = mainSubject;
    }
}