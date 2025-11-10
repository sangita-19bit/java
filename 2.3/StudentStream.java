import java.util.*;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " - Marks: " + marks;
    }
}

public class StudentStream {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Rahul", 82.5),
            new Student("Priya", 68.0),
            new Student("Anita", 91.0),
            new Student("Karan", 75.5),
            new Student("Meena", 88.0)
        );

        System.out.println("Students scoring above 75% (sorted by marks):");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted((s1, s2) -> Double.compare(s1.marks, s2.marks))
                .map(s -> s.name + " (" + s.marks + "%)")
                .forEach(System.out::println);
    }
}
