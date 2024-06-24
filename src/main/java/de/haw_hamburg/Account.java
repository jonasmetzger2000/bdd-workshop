package de.haw_hamburg;

import lombok.Getter;

import java.util.LinkedList;

@Getter
public class Account {

    private final String name;
    private LinkedList<Transaction> transactions;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.transactions = new LinkedList<>();
    }


    public Transaction transaction(Account from, double amount) {
        checkBalanceMin(this, amount);

        final Transaction transaction = new Transaction(amount, this, from);
        transactions.add(transaction);
        balance -= amount;
        from.balance += amount;
        return transaction;
    }

    private static void checkBalanceMin(Account account, double amount) {
        if (account.balance < amount) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
