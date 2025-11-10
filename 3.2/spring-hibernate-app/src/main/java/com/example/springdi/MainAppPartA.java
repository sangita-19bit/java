package com.example.springdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppPartA {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = ctx.getBean(Student.class);
        student.displayInfo();

        ctx.close();
    }
}
