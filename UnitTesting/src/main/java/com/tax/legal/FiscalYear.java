package com.tax.legal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rory.payne on 19/03/14.
 */
public class FiscalYear extends Object implements Serializable {

    public List<Rates> FYSettings = new ArrayList<Rates>();

    public void addFY(Rates rates) {
        FYSettings.add(rates);
    }

    public Rates getFY(int x) {
        return FYSettings.get(x);
    }

    @Override
    public String toString() {
        String total = "\n";
        for(int i=0; i<FYSettings.size(); i++) {
            Rates r = FYSettings.get(i);
            total += "Option: " + i+1 + " - " + r.toString();
        }
        return total;
    }

    public String fiscalToString() {
        String total = "\n";
        for(int i=0; i<FYSettings.size(); i++) {
            Rates r = FYSettings.get(i);
            int option = i+1;
            total += "\nOption: " + option + " - " + r.fiscalToString();
        }
        return total;
    }
}
