package com.ems.model;

import java.sql.Date;

public class Employee {
    private int empId;
    private String name;
    private String email;
    private String type; // FULL_TIME or PART_TIME
    private double basicPay;
    private double allowances;
    private Date joinDate;

    public Employee() {
    }

    public Employee(int empId, String name, String email, String type, double basicPay, double allowances,
            Date joinDate) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.type = type;
        this.basicPay = basicPay;
        this.allowances = allowances;
        this.joinDate = joinDate;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
