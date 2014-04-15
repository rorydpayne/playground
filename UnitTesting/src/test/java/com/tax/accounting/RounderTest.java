package com.tax.accounting;

import com.tax.accounting.Rounder;
import junit.framework.TestCase;
import org.junit.Test;

/**
* Created by rory.payne on 18/03/14.
*/
public class RounderTest extends TestCase {
    Rounder r = new Rounder(2);
    double result, expected;

    @Test
    public void testRounderLessThanHalf() {
        result = r.round(123.111);
        expected = 123.11;
        assertEquals(expected, result);
    }

    @Test
    public void testRounderMoreThanHalf() {
        result = r.round(123.119);
        expected = 123.12;
        assertEquals(expected, result);
    }

    @Test
    public void testRounderHalfUp() {
        result = r.round(123.125);
        expected = 123.13;
        assertEquals(expected, result);
    }
}
