package com.labs.Module2.domain;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private int numOfClients = 0;

    private static Bank myBank = new Bank();

    private Bank() {
    }

    public Customer getCustomer(int customerNum) {
        if (customerNum<=customers.size()) {
            return customers.get(customerNum);
        }
        return null;
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        numOfClients++;
    }

    public int getNumOfClients() {
        return numOfClients;
    }

    public static Bank getBank() {
        return myBank;
    }
}
