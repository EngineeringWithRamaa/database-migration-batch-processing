package com.engineeringwithramaa.databasebackup.model.users_db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_TABLE")
public class User {
    @Id
    private int id;
    private String name;
    private String department;
    private Long Salary;

    public User() {
    }

    public User(int id, String name, String department, Long salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        Salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getSalary() {
        return Salary;
    }

    public void setSalary(Long salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", Salary=" + Salary +
                '}';
    }
}