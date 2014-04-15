package com.tax.user;

import com.tax.accounting.Rounder;
import com.tax.engine.NICalculator;
import com.tax.engine.StudentLoanCalculator;
import com.tax.engine.TaxCalculator;
import com.tax.legal.FiscalYear;
import com.tax.legal.Rates;

import java.io.*;
import java.util.*;


/**
 * Created by rory.payne on 17/03/14.
 */
public class Main {

    static String fileName = null;
    static FiscalYear FY = new FiscalYear();
    static boolean running = true;
    static Scanner in = new Scanner(System.in);



    public static void main(String[] args) {
        while(running){
            System.out.println("<#################### PAY CALCULATOR ####################>");
            System.out.println("\nEnter 0 to load Settings" + "\nEnter 1 to add Tax Rules" + "\nEnter 2 for Take Home Pay Calculator" + "\nEnter 3 to exit");
            System.out.println("\n\n\n");
            System.out.println("<----------------------------------------------------->" + "\n");
            int answer = in.nextInt();
            switch(answer){
                case 0:
                    System.out.println("\nchoose configuration file" + "\n1. Test" + "\n2. Production");
                    if (in.nextInt() == 2) {
                        fileName = "Production";
                    } else {
                        fileName = "Test";
                    }
                    loadScript(fileName);
                    break;
                case 1:
                    addRules();
                    break;
                case 2:
                    System.out.println("<#################### PAY CALCULATOR ####################>");
                    System.out.println(FY.fiscalToString());
                    System.out.println("\nEnter Option number corresponding to FY");
                    int FYRule = in.nextInt();
                    Rates rates = FY.getFY(FYRule-1);

                    System.out.println("Annual Salary (£): ");
                    double annualSalary = in.nextDouble();

                    System.out.println("Student Loan (Y/N)?" + "\nEnter 1 for YES" + "\nEnter 2 for NO");
                    int ans = in.nextInt();
                    boolean studentLoan = false;
                    if(ans == 1) {
                        studentLoan = true;
                    }
                    User user = new User(annualSalary, studentLoan);

                    System.out.println("precision (decimal places): ");
                    int places = in.nextInt();
                    Rounder rounder = new Rounder(places);

                    TaxCalculator tx = new TaxCalculator(user, rates, rounder);
                    NICalculator ni = new NICalculator(user, rates, rounder);
                    StudentLoanCalculator sl = new StudentLoanCalculator(user, rates, rounder);

                    System.out.println("Annual income tax is: £" + tx.getTax());
                    System.out.println("Annual national insurance is: £" + ni.getNI());
                    if (studentLoan) {System.out.println("Annual student loan repayment is: £" + sl.getStudentLoan());}
                    System.out.println("Take home pay: £" + (annualSalary - tx.getTax() - ni.getNI()));
                    System.out.println("<----------------------------------------------------->" + "\n");

                    break;
                case 3:
                    saveAndQuit();
                    break;
            }
        }
        System.exit(0);
    }

    private static void addRules() {
        String Fiscal;
        double persAllowance;
        double allowanceIncomeLimit;
        double basicBand;
        double highBand;
        double basicRate;
        double highRate;
        double addRate;
        double lowerNILimit;
        double upperNILimit;
        double stdNIRate;
        double addNIRate;
        double studentLoanRate;
        double studentLoanRepaymentThreshold;

        System.out.println("\nEnter Fiscal Year (YY/YY): ");
        Fiscal = in.next();

        System.out.println("\nEnter Personal Allowance: ");
        persAllowance = in.nextDouble();

        System.out.println("\nEnter Income Limit for Personal Allowance: ");
        allowanceIncomeLimit = in.nextDouble();

        System.out.println("\nEnter Basic Band Upper Limit: ");
        basicBand = in.nextDouble();

        System.out.println("\nEnter Higher Band Upper Limit: ");
        highBand = in.nextDouble();

        System.out.println("\nEnter Basic Income Tax Rate: ");
        basicRate = in.nextDouble();

        System.out.println("\nEnter Higher Income Tax Rate: ");
        highRate = in.nextDouble();

        System.out.println("\nEnter Additional Income Tax Rate: ");
        addRate = in.nextDouble();

        System.out.println("\nEnter Lower NI earnings limit (weekly pay): ");
        lowerNILimit = in.nextDouble();

        System.out.println("\nEnter Upper NI earnings limit (weekly pay): ");
        upperNILimit = in.nextDouble();

        System.out.println("\nEnter Standard NI deduction rate: ");
        stdNIRate = in.nextDouble();

        System.out.println("\nEnter Additional NI deduction rate: ");
        addNIRate = in.nextDouble();

        System.out.println("\nEnter student loan repayment rate: ");
        studentLoanRate = in.nextDouble();

        System.out.println("\nEnter student loan repayment threshold: ");
        studentLoanRepaymentThreshold = in.nextDouble();

        Rates r = new Rates(Fiscal, persAllowance, allowanceIncomeLimit, basicBand, highBand, basicRate, highRate, addRate, lowerNILimit, upperNILimit, stdNIRate, addNIRate, studentLoanRate, studentLoanRepaymentThreshold);
        FY.addFY(r);
    }

    private static void saveAndQuit() {
        System.out.println("\nSave As:" + "\n1. Test" + "\n2. Production" + "\n3. Discard Changes");
        int answer = in.nextInt();
        if (answer == 3) {
            running = false;
        } else  if (answer == 2) {
            fileName = "Production";
            running = false;
            FileOutputStream fos = null;
            ObjectOutputStream out = null;
            try {
                fos = new FileOutputStream(fileName);
                out = new ObjectOutputStream(fos);
                out.writeObject(FY);
                fos.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (answer == 1) {
            fileName = "Test";
            running = false;
            FileOutputStream fos = null;
            ObjectOutputStream out = null;
            try {
                fos = new FileOutputStream(fileName);
                out = new ObjectOutputStream(fos);
                out.writeObject(FY);
                fos.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadScript(String name) {

        FileInputStream fis = null;
        ObjectInputStream in = null;
        File file = new File(name);
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                in = new ObjectInputStream(fis);
                FY = (FiscalYear) in.readObject();
                fis.close();
                in.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not Found");
        }
    }
}
