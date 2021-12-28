package com.labs.Module2.myBank.ATM;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.labs.Module2.domain.*;

import javax.swing.*;

public class myBankATM extends javax.swing.JFrame {

    private javax.swing.JTextField amountField;
    private javax.swing.JButton balanceButton;
    private javax.swing.JButton depositButton;
    private javax.swing.JButton eightButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JButton fiveButton;
    private javax.swing.JButton fourButton;
    private javax.swing.JTextArea historyArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nineButton;
    private javax.swing.JButton oneButton;
    private javax.swing.JButton pointButton;
    private javax.swing.JButton sevenButton;
    private javax.swing.JButton sixButton;
    private javax.swing.JTextField statusField;
    private javax.swing.JButton threeButton;
    private javax.swing.JButton twoButton;
    private javax.swing.JButton withdrawButton;
    private javax.swing.JButton zeroButton;

    Bank bank;
    Customer currentCustomer;
    Account currentAccount;

    public myBankATM() {
        bank = Bank.getBank();

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

        this.setLocationRelativeTo(null);

        initComponents();

        for (Component c : jPanel3.getComponents())
        {
            if ((c.getClass() == JButton.class ) && (((JButton)c).getText()!= "ENTER"))
            {
                JButton currentButton = (JButton) c;
                currentButton.addActionListener(e -> addDigit(e));
            }
        }
    }
    @SuppressWarnings("unchecked")
    private void initComponents()
    {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        balanceButton = new javax.swing.JButton();
        depositButton = new javax.swing.JButton();
        withdrawButton = new javax.swing.JButton();
        amountField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        oneButton = new javax.swing.JButton();
        twoButton = new javax.swing.JButton();
        threeButton = new javax.swing.JButton();
        fourButton = new javax.swing.JButton();
        fiveButton = new javax.swing.JButton();
        sixButton = new javax.swing.JButton();
        sevenButton = new javax.swing.JButton();
        eightButton = new javax.swing.JButton();
        nineButton = new javax.swing.JButton();
        zeroButton = new javax.swing.JButton();
        pointButton = new javax.swing.JButton();
        enterButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyArea = new javax.swing.JTextArea();
        statusField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("my Bank ATM");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(801, 350));

        jPanel1.setLayout(new GridLayout(2, 1));

        jPanel2.setLayout(new GridLayout(4, 1));

        balanceButton.setText("Check account balance");
        balanceButton.setEnabled(false);
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                balanceButtonActionPerformed(evt);
            }
        });
        jPanel2.add(balanceButton);

        depositButton.setText("Make a deposit");
        depositButton.setEnabled(false);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });
        jPanel2.add(depositButton);

        withdrawButton.setText("Make a withdrawal");
        withdrawButton.setEnabled(false);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });
        jPanel2.add(withdrawButton);

        amountField.setToolTipText("");
        jPanel2.add(amountField);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new GridLayout(4,3));

        oneButton.setText("1");
        jPanel3.add(oneButton);

        twoButton.setText("2");
        jPanel3.add(twoButton);

        threeButton.setText("3");
        jPanel3.add(threeButton);

        fourButton.setText("4");
        jPanel3.add(fourButton);

        fiveButton.setText("5");
        jPanel3.add(fiveButton);

        sixButton.setText("6");
        jPanel3.add(sixButton);

        sevenButton.setText("7");
        jPanel3.add(sevenButton);

        eightButton.setText("8");
        jPanel3.add(eightButton);

        nineButton.setText("9");
        jPanel3.add(nineButton);

        zeroButton.setText("0");
        jPanel3.add(zeroButton);


        pointButton.setText(".");
        jPanel3.add(pointButton);

        enterButton.setText("ENTER");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });
        jPanel3.add(enterButton);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, BorderLayout.LINE_START);

        historyArea.setEditable(false);
        historyArea.setColumns(20);
        historyArea.setRows(5);
        jScrollPane1.setViewportView(historyArea);

        getContentPane().add(jScrollPane1, BorderLayout.CENTER);

        statusField.setEditable(false);
        statusField.setText("Welcome to my Bank! Enter the client ID and press Enter...");
        getContentPane().add(statusField, BorderLayout.PAGE_END);

        pack();
    }

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        int customerID = 0;
        try {
            customerID = Integer.parseInt(amountField.getText());
            currentCustomer = bank.getCustomer(customerID);
            currentAccount = currentCustomer.getAccount(0);
            historyArea.append("Customer with ID = " + customerID+" is "+currentCustomer.getLastName()+", "+ currentCustomer.getFirstName()+"\n");
            balanceButton.setEnabled(true);
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            enterButton.setEnabled(false);
        }
        catch (Exception ex)
        {
            historyArea.append("ERROR: Customer not found or wrong Customer ID!\n");
        }

        amountField.setText("");
        statusField.setText("Customer: "+ currentCustomer.getLastName()+", "+ currentCustomer.getFirstName());
    }
    private void balanceButtonActionPerformed(ActionEvent evt)
    {

        historyArea.append("Balance of " + currentCustomer.getFirstName()+ "'s first account is $" + currentAccount.getBalance());
        if (currentAccount instanceof CheckingAccount)
        {
            historyArea.append(". This is a Checking Account with overdraft protection $" +((CheckingAccount)currentAccount).getOverdraftAmount()+"\n");
        }
        else
        {
            historyArea.append(". This is a Savings Account with interest rate" + ((SavingsAccount)currentAccount).getInterestRate()+"%\n");
        }
        statusField.setText("READY");
    }

    private void depositButtonActionPerformed(ActionEvent evt)
    {
        try {
            double amt = Double.parseDouble(amountField.getText());
            currentAccount.deposit(amt);
            historyArea.append("Deposit: $"+amt+", new balance is $"+currentAccount.getBalance()+"\n");
            statusField.setText("READY");

        }
        catch (Exception e)
        {
            historyArea.append("ERROR: can't complete deposit operation");
            statusField.setText("ERROR");
        }
        amountField.setText("");
    }

    private void withdrawButtonActionPerformed(ActionEvent evt)
    {
        try {
            double amt = Double.parseDouble(amountField.getText());
            if (currentAccount.withdraw(amt))
            historyArea.append("Deposit: $"+amt+", new balance is $"+currentAccount.getBalance()+"\n");
            statusField.setText("READY");
        }
        catch (OverdraftException ex)
        {
            historyArea.append("ERROR: Insufficient funds!\n");
            statusField.setText("ERROR");
        }
        catch (Exception e)
        {
            historyArea.append("ERROR: can't complete deposit operation\n");
            statusField.setText("ERROR");
        }
        amountField.setText("");
    }

    private void addDigit(ActionEvent evt)
    {
        amountField.setText(amountField.getText()+((JButton)evt.getSource()).getText());
    }


    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(myBankATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(myBankATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(myBankATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(myBankATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }




        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new myBankATM().setVisible(true);
            }
        });
    }

}
