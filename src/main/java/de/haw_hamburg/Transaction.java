package de.haw_hamburg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Repräsentiert eine Transaktion über ein gegebenen Betrag mittels Sender und Empfänger
 */
@Getter
@RequiredArgsConstructor
public class Transaction {

    private final double betrag;
    private final Account sender;
    private final Account empfaenger;

}
