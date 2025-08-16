package com.bank.model;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    public Account createAccount(String name, double initialDeposit) {
        Account acc = new Account(nextAccountNumber, name, initialDeposit);
        accounts.put(nextAccountNumber, acc);
        nextAccountNumber++;
        return acc;
    }

    public Account getAccount(int accNumber) {
        return accounts.get(accNumber);
    }
}
