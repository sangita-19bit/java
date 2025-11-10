package com.example.springdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course course() {
        return new Course("Spring Framework - Java Config");
    }

    @Bean
    public Student student() {
        Student s = new Student(course()); // constructor injection
        s.setName("Rohan"); // optional setter
        return s;
    }
}
