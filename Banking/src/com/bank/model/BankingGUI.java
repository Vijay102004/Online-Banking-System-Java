package com.bank.model;

import javax.swing.*;
import java.awt.*;

public class BankingGUI extends JFrame {
    private Bank bank;

    public BankingGUI() {
        bank = new Bank();

        setTitle("🏦 Online Banking System");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton createAccBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton checkBalBtn = new JButton("Check Balance");
        JButton exitBtn = new JButton("Exit");

        add(createAccBtn);
        add(depositBtn);
        add(withdrawBtn);
        add(transferBtn);
        add(checkBalBtn);
        add(exitBtn);

        // Create Account
        createAccBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter Account Holder Name:");
            double deposit = Double.parseDouble(JOptionPane.showInputDialog("Enter Initial Deposit:"));
            Account acc = bank.createAccount(name, deposit);
            JOptionPane.showMessageDialog(this, "🎉 Account Created!\nAccount Number: " + acc.getAccountNumber());
        });

        // Deposit
        depositBtn.addActionListener(e -> {
            int accNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Deposit Amount:"));
            Account acc = bank.getAccount(accNo);
            if (acc != null) {
                acc.deposit(amount);
                JOptionPane.showMessageDialog(this, "✅ Deposited " + amount + "\nNew Balance: " + acc.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "❌ Account Not Found!");
            }
        });

        // Withdraw
        withdrawBtn.addActionListener(e -> {
            int accNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Withdraw Amount:"));
            Account acc = bank.getAccount(accNo);
            if (acc != null && acc.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "✅ Withdrawn " + amount + "\nRemaining Balance: " + acc.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "❌ Transaction Failed!");
            }
        });

        // Transfer
        transferBtn.addActionListener(e -> {
            int fromAcc = Integer.parseInt(JOptionPane.showInputDialog("Enter Your Account Number:"));
            int toAcc = Integer.parseInt(JOptionPane.showInputDialog("Enter Target Account Number:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount to Transfer:"));
            Account sender = bank.getAccount(fromAcc);
            Account receiver = bank.getAccount(toAcc);
            if (sender != null && receiver != null && sender.transfer(receiver, amount)) {
                JOptionPane.showMessageDialog(this, "✅ Transferred " + amount + " to " + receiver.getHolderName());
            } else {
                JOptionPane.showMessageDialog(this, "❌ Transfer Failed!");
            }
        });

        // Check Balance
        checkBalBtn.addActionListener(e -> {
            int accNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Account Number:"));
            Account acc = bank.getAccount(accNo);
            if (acc != null) {
                JOptionPane.showMessageDialog(this, "💰 Balance for " + acc.getHolderName() + ":\n" + acc.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "❌ Account Not Found!");
            }
        });

        // Exit
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
