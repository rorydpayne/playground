package com.tax.engine;

import com.tax.accounting.Rounder;
import com.tax.legal.Rates;
import com.tax.user.User;

/**
 * Created by rory.payne on 21/03/14.
 */
public class StudentLoanCalculator {
    private User user;
    private Rates rates;
    private Rounder r;

    public StudentLoanCalculator(User user, Rates rates, Rounder r) {
        this.user=user;
        this.rates=rates;
        this.r=r;
    }

    public double getStudentLoan() {
        boolean studentLoan = user.isStudentLoan();
        double salary = user.getAnnualSalary();
        double studentLoanRate = rates.getStudentLoanRate();
        double studentLoanRepaymentThreshold = rates.getStudentLoanRepaymentThreshold();
        double studentLoanRepayment;
        if (studentLoan) {
            double taxable = salary - studentLoanRepaymentThreshold;
            if (taxable < 0){
                taxable = 0;
            }

            studentLoanRepayment = taxable * studentLoanRate;

        } else {
            studentLoanRepayment = 0;
        }
        return r.round(studentLoanRepayment);
    }
}
