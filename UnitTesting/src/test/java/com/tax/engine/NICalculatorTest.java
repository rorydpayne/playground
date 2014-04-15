package com.tax.engine;

import com.tax.accounting.Rounder;
import com.tax.legal.FiscalYear;
import com.tax.legal.Rates;
import com.tax.user.User;
import junit.framework.Assert;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;

/**
* Created by rory.payne on 18/03/14.
*/
public class NICalculatorTest {

    double result, expected;

    @Test
    public void testGetNIWhenPayLessThanLowerLimit() {
        //SETUP
        Rounder rounder = new Rounder(2);
        User user = new User(9, false);
        Rates rates = new Rates("",0,0,0,0,0,0,0,10,15,0,0, 0, 0);
        NICalculator ni = new NICalculator(user, rates, rounder);

        //exercise
        result = ni.getNI();

        //verify
        expected = 0;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetNIWhenMoreThanLowerLimitAndLessThanUpperLimitDifferenceChargedAtTwelvePercent() {
        //SETUP
        Rounder rounder = new Rounder(2);
        User user = new User(15, false);
        Rates rates = new Rates("",10,0,0,0,0,0,0,10,15,0,0, 0, 0);
        NICalculator ni = new NICalculator(user, rates, rounder);

        //exercise
        result = ni.getNI();

        //verify
        expected = .12*5;
        Assert.assertEquals(expected, result);
    }
    @Test
    public void testGetNIWhenMoreThanUpperLimitDifferenceChargedAtTwoPercent() {
        //SETUP
        Rounder rounder = new Rounder(2);
        User user = new User(20, false);
        Rates rates = new Rates("",10,0,0,0,0,0,0,10,15,0,0, 0, 0);
        NICalculator ni = new NICalculator(user, rates, rounder);

        //exercise
        result = ni.getNI();

        //verify
        expected = .12*5 + 0.02*5;
        Assert.assertEquals(expected, result);
    }



}
