package com.test;

import java.io.*;
import java.util.ArrayList;

public class Launcher {

    public static File file1 = new File("./files/Test_Task1.csv");
    public static File file2 = new File("./files/Test_Task.csv");
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Department> departments = new ArrayList<>();


    public static void main(String[] args) {

        // read the first file and save values into the Class Employee
        Thread thread1 = new Thread(() -> {
            try {
               var br = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));

                String line1;
                br.readLine(); // read the header and don't save it
                while ((line1 = br.readLine()) != null) {
                    String[] split = line1.split(";");
                    Employee employee = new Employee(split[0], split[1], split[2], split[3]);
                    employees.add(employee);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // read the second file and save values into the Class Department
        Thread thread2 = new Thread(() -> {
            try {
               var br = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                String line1;
                br.readLine(); // read the header and don't save it
                while ((line1 = br.readLine()) != null) {

                    String[] split = line1.split(";");
                    Department department = new Department(split[0], split[1]);
                    //System.out.println(department);
                    departments.add(department);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        for (Employee employee : employees) {
            var department = departments.stream().filter(x -> x.getId().equals(employee.getDepartmentId())).findFirst();
            if (department.isPresent()) {
                employee.setDepartment(department.get());
            } else {
                employee.setDepartment(new Department(null, "Unknown"));
            }

            System.out.println(employee.toString());
        }
    }


}
