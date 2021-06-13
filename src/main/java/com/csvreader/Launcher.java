package com.csvreader;

import java.util.ArrayList;
import java.util.List;

import static com.csvreader.Utils.loadDepartmentData;
import static com.csvreader.Utils.loadEmployeeData;

public class Launcher {

    public static List<Employee> employees = new ArrayList<>();
    public static List<Department> departments = new ArrayList<>();


    public static void main(String[] args) {

        // read the first file and save values into the Class Employee
        Thread thread1 = new Thread(() -> {
            employees = loadEmployeeData();
        });

        // read the second file and save values into the Class Department
        Thread thread2 = new Thread(() -> {
            departments = loadDepartmentData();
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // inner join two lists of employees and departments
        System.out.println("Name  Surname  Department");
        System.out.println("--------------------------");
        for (Employee employee : employees) {
            var department = departments.stream().filter(x -> x.getId()
                    .equals(employee.getDepartmentId())).findFirst();
            if (department.isPresent()) {
                employee.setDepartment(department.get());
            } else {
                employee.setDepartment(new Department(null, "Unknown"));
            }

            System.out.println(employee.getName() + " " + employee.getSurname() + " " + employee.getDepartment().getName());
            System.out.println("--------------------------");
        }
    }
}
