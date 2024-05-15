package com.exl_convertor.interview_questions.java8_program;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeProgram {

    public static void main(String[] args){

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
        employeeList.add(new Employee(278, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        getCountOfMaleFemale(employeeList);
        getAllDepartments(employeeList);
        // get employee who joined after 2015
        getAllEmployeeLateJoined(employeeList);
        //find Duplicate Employee
        findDuplicate(employeeList);
    }

    private static void findDuplicate(List<Employee> employeeList) {
        Set<String> set = new HashSet<>();
        List<Employee> list = employeeList.stream().filter(e -> !set.add(e.getName())).collect(Collectors.toList());
       System.out.println("Duplicate list - " + Arrays.asList(list));
    }

    private static void getAllEmployeeLateJoined(List<Employee> employeeList) {
        List<String> list = employeeList.stream().filter(e -> e.yearOfJoining > 2015)
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(Arrays.asList(list));
    }

    private static void getAllDepartments(List<Employee> employeeList) {
        List<String> list = employeeList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(Arrays.asList("All Departments" + list));
    }

    private static void getCountOfMaleFemale(List<Employee> employeeList) {

        Map<String, Long> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee :: getGender, Collectors.counting()));

        System.out.println("Male Employee counts are - " + map.get("Male"));
        System.out.println("Female Employee counts are - " + map.get("Female"));
    }
}

class Employee {

    int id;
    String name;
    int age;
    String gender;
    String department;
    int yearOfJoining;
    double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Id : " + id
                + ", Name : " + name
                + ", age : " + age
                + ", Gender : " + gender
                + ", Department : " + department
                + ", Year Of Joining : " + yearOfJoining
                + ", Salary : " + salary;
    }
}
