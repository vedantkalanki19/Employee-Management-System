package com.ems.service;

import com.ems.model.Employee;

public class SalaryCalculator {

    private static final double DEDUCTION_RATE = 0.05; // 5%

    public static double calculateGrossSalary(Employee emp) {
        return emp.getBasicPay() + emp.getAllowances();
    }

    public static double calculateDeduction(double grossSalary) {
        return grossSalary * DEDUCTION_RATE;
    }

    public static double calculateNetSalary(Employee emp) {
        double gross = calculateGrossSalary(emp);
        double deduction = calculateDeduction(gross);
        return gross - deduction;
    }
}
