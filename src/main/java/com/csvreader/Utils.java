package com.csvreader;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String SEMICOLON = ";";
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Department> departments = new ArrayList<>();


    public static Employee parseCSVEmployee(String[] columns) {

        Employee employee = new Employee();
        employee.setId(columns[0]);
        employee.setName(columns[1]);
        employee.setSurname(columns[2]);
        employee.setDepartmentId(columns[3]);

        return employee;
    }

    public static Department parseCSVDepartment(String[] columns) {

        Department department = new Department();
        department.setId(columns[0]);
        department.setName(columns[1]);

        return department;
    }

    /**
     * saves the data to class Employee and adds to List
     *
     * @return list of Employees
     */
    public static List<Employee> loadEmployeeData() {
        List<String> csvData = loadCsvData(readFromResources("Test Task1.csv"));
        employees = new ArrayList<>();
        for (String employeeData : csvData) {
            String[] columns = employeeData.split(SEMICOLON);
            Employee employee = parseCSVEmployee(columns);
            employees.add(employee);
        }

        return employees;
    }

    /**
     * saves the data to class Department and adds to List
     *
     * @return list of Departments
     */
    public static List<Department> loadDepartmentData() {
        List<String> csvData = loadCsvData(readFromResources("Test_Task.csv"));
        departments = new ArrayList<>();

        for (String departmentData : csvData) {
            String[] columns = departmentData.split(SEMICOLON);
            Department department = parseCSVDepartment(columns);
            departments.add(department);
        }

        return departments;
    }

    /**
     * reads data from csv file
     *
     * @param file
     * @return List<String>
     */
    private static List<String> loadCsvData(File file) {
        List<String> result = new ArrayList<>();
        final FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                result.add(bufferedReader.readLine());
            }
            result.remove(0);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * reads csv files from folder "resources"
     *
     * @param fileName
     * @return File
     */
    public static File readFromResources(String fileName) {
        // get the file url
        URL resource = Utils.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        } else {
            try {
                return new File(resource.toURI());
                // failed if files have whitespaces or special characters
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //returns quantity of employees
    public static long employeeCount() {
        return loadEmployeeData().size();
    }

    //returns quantity of departments
    public static long departmentCount() {
        return loadDepartmentData().size();
    }
}
