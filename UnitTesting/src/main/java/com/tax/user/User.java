package com.tax.user;

/**
 * Created by rory.payne on 18/03/14.
 */
public class User {

    private double annualSalary;
    private boolean studentLoan;


    public User() {
        annualSalary = 0;
        studentLoan = false;
    }

    public User(double annualSalary, boolean studentLoan) {
        this.annualSalary = annualSalary;
        this.studentLoan = studentLoan;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {

        this.annualSalary = annualSalary;
    }

    public boolean isStudentLoan() {
        return studentLoan;
    }

    public void setStudentLoan(boolean studentLoan) {
        this.studentLoan = studentLoan;
    }
}
