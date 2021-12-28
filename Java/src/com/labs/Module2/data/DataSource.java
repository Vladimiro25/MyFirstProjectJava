package com.labs.Module2.data;

import com.labs.Module2.domain.Bank;
import com.labs.Module2.domain.CheckingAccount;
import com.labs.Module2.domain.Customer;
import com.labs.Module2.domain.SavingsAccount;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DataSource {
    private File dataFile;

    public DataSource(String dataFilePath) {
        dataFile = new File(dataFilePath);
    }

    public void loadData() throws IOException
    {
        Scanner input = new Scanner(dataFile);
        Bank bank = Bank.getBank();
        Customer customer;

        int numOfCustomer = input.nextInt();
        for (int i = 0; i < numOfCustomer; i++) {
            String firstName = input.next();
            String lastName = input.next();

            bank.addCustomer(new Customer(firstName,lastName));
            customer=bank.getCustomer(i);

            int numOfAccounts = input.nextInt();
            while (numOfAccounts-- > 0)
            {
                char accountType = input.next().charAt(0);
                switch (accountType)
                {
                    case 'S':
                    {
                        float initBalance = input.nextFloat();
                        float interestRate = input.nextFloat();
                        customer.addAccount(new SavingsAccount(initBalance,interestRate));
                        break;
                    }
                    case 'C':
                    {
                        float initBalance = input.nextFloat();
                        float overdraftAmount = input.nextFloat();
                        customer.addAccount(new CheckingAccount(initBalance,overdraftAmount));
                        break;
                    }
                }
            }
        }
    }
}
