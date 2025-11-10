package com.example.springdi;

public class Student {
    private Course course;
    private String name;

    // Constructor injection
    public Student(Course course) {
        this.course = course;
    }

    public Student() { }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("Student name: " + (name != null ? name : "N/A"));
        System.out.println("Enrolled in course: " + (course != null ? course.getCourseName() : "N/A"));
    }
}
