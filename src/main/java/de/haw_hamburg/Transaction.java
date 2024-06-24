package de.haw_hamburg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Transaction {

    private final double amount;
    private final Account sender;
    private final Account receiver;

}
