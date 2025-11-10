package com.example.hibernatecrud;

import java.util.List;

public class StudentMain {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Create
        Integer id1 = dao.createStudent(new Student("Amit", 20));
        Integer id2 = dao.createStudent(new Student("Priya", 22));
        System.out.println("Created students with IDs: " + id1 + ", " + id2);

        // Read one
        Student s = dao.getStudent(id1);
        System.out.println("Fetched student: " + s);

        // Read all
        List<Student> list = dao.getAllStudents();
        System.out.println("All students:");
        for (Student st : list) System.out.println(st);

        // Update
        s.setAge(21);
        dao.updateStudent(s);
        System.out.println("Updated student: " + dao.getStudent(id1));

        // Delete
        dao.deleteStudent(id2);
        System.out.println("After deletion, all students:");
        for (Student st : dao.getAllStudents()) System.out.println(st);

        HibernateUtil.shutdown();
    }
}
