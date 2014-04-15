package com.tax.accounting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;

/**
 * Created by rory.payne on 18/03/14.
 */
public class Rounder {
    private int scale;
    //private RoundingMode rule = RoundingMode.HALF_UP;

    public Rounder(int scale) {
        this.scale = scale;
        //this.rule = rule;
    }

    public double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
