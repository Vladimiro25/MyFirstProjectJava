package com.labs.Module2.test;

import com.labs.Module2.domain.*;

public class TestAccount{
    public static void main(String[] args) {

        Bank bank = Bank.getBank();

        Customer firstCustomer = new Customer("Vladimiro","Vladimir");
        Customer secondCustomer = new Customer("Elena ","Vasilievna");

        SavingsAccount vladimiroSavings = new SavingsAccount(1000,5);
        CheckingAccount vladimiroChecking = new CheckingAccount(5000,1000);
        CheckingAccount elenaChecking = new CheckingAccount(500,100);

        firstCustomer.addAccount(vladimiroSavings);
        firstCustomer.addAccount(vladimiroChecking);
        secondCustomer.addAccount(elenaChecking);

        bank.addCustomer(firstCustomer);
        bank.addCustomer(secondCustomer);

        displayCustomer(bank);

        bank.getCustomer(0).getAccount(0).deposit(2000);
        try {
            bank.getCustomer(0).getAccount(1).withdraw(5500);
        }
        catch (OverdraftException ex)
        {
            System.out.println(ex.getMessage() + ": $" + ex.getDeficit()+"!\n");
        }
        catch (Exception ex) {
            System.out.println("Something went wrong!");
        }
        ((SavingsAccount)bank.getCustomer(0).getAccount(0)).addInterestRate();
        System.out.println(" ");

        displayCustomer(bank);


    }

    private static void displayCustomer(Bank bank) {
        System.out.println(bank.getCustomer(0));
        System.out.println(bank.getCustomer(1));
    }
}
