package de.haw_hamburg;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Repräsentiert ein de.haw_hamburg.Konto im echten Leben. Es können Überweisungen über Konten stattfinden, als auch Beträge
 * ausgezahlt werden.
 */
@Getter
public class Account {

    private final String name;
    private LinkedList<Transaction> transactions;
    private double balance;

    /**
     * Erstellt ein neues de.haw_hamburg.Konto
     * @param balance Kontostand
     */
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.transactions = new LinkedList<>();
    }

    /**
     * Überweist dem Kunden empfaenger den Betrag amount über, falls der Sender genügend Geld auf dem de.haw_hamburg.Konto hat.
     * @param empfaenger das empfängende de.haw_hamburg.Konto
     * @throws IllegalArgumentException wenn der Kontostand des Senders nicht ausreicht um den Betrag anzahl auszuzahlen
     * @param amount wieviel Geld dem empfaenger ausgezahlt werden soll
     * @return Die stattgefundene Transaktion
     */
    public Transaction ueberweisen(Account empfaenger, double amount) {
        checkBalanceMin(this, amount);

        final Transaction transaction = new Transaction(amount, this, empfaenger);
        transactions.add(transaction);
        balance -= amount;
        empfaenger.balance += amount;
        return transaction;
    }

    /**
     * Zahlt dem Kunden den gewünschten Betrag aus
     * @param anzahl wieviel Geld aus dem de.haw_hamburg.Konto entfernt werden soll
     * @throws IllegalArgumentException wenn der Kontostand nicht ausreicht um den Betrag anzahl auszuzahlen
     * @return der neue Kontostand nach dem Auszahlen
     */
    public double auszahlen(double anzahl) {
        checkBalanceMin(this, anzahl);

        balance -= anzahl;
        return balance;
    }

    private static void checkBalanceMin(Account account, double amount) {
        if (account.balance < amount) {
            throw new IllegalArgumentException("zu wenig geld auf dem de.haw_hamburg.Konto");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
