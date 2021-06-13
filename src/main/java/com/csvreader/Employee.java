package com.csvreader;

public class Employee {

    private String id;
    private String name;
    private String surname;
    private String departmentId;
    private Department department;

    public Employee(String id, String name, String surname, String departmentId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.departmentId = departmentId;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " "
                + getSurname() + " " + getDepartmentId();
    }
}
