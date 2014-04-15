package com.tax.engine;

import com.tax.accounting.Rounder;
import com.tax.legal.Rates;
import com.tax.user.User;

/**
 * Created by rory.payne on 17/03/14.
 */
public class NICalculator {

    private User user;
    private Rates rates;
    private Rounder r;

    public NICalculator(User user, Rates rates, Rounder r) {
        this.user = user;
        this.rates = rates;
        this.r = r;
    }

    public double getNI() {
        double salary = user.getAnnualSalary();
        double lowerLimit = rates.getLowerNILimit();
        double upperLimit = rates.getUpperNILimit();
        double NIRate = rates.getStdNIRate();
        double addNIRate = rates.getAddNIRate();

        double weeklyWage = salary/52;
        double basicNI = 0;
        double addNI = 0;
        double NI = 0;

        if (weeklyWage > lowerLimit) {
            if (weeklyWage > upperLimit) {
                basicNI = (upperLimit - lowerLimit)*NIRate;
                addNI = (weeklyWage - upperLimit)*addNIRate;
            } else {
                basicNI = (weeklyWage - lowerLimit)*NIRate;
            }
            NI = basicNI + addNI;
        }

        double totNI = NI*52;
        return r.round(totNI);
    }
}
