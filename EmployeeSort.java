import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - Age: " + age + ", Salary: " + salary;
    }
}

public class EmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 28, 55000));
        employees.add(new Employee("Alice", 24, 60000));
        employees.add(new Employee("Bob", 30, 45000));
        employees.add(new Employee("Charlie", 26, 70000));

        System.out.println("Original List:");
        employees.forEach(System.out::println);

        // Sort by Name (Alphabetically)
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by Age (Ascending)
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by Salary (Descending)
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }
}
