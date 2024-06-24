package de.haw_hamburg;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.data.Offset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class KontoStepDefinition {

    private Map<String, Account> accountMap = new HashMap<>();
    private List<Transaction> transaction = new ArrayList<>();
    private List<Boolean> transactionStatus = new ArrayList<>();

    @Given("Account {string} has a balance of {double}€")
    public void createAccount(String kontoName, double kontostand) {
        accountMap.put(kontoName, new Account(kontoName, kontostand));
    }

    @When("Account {string} transfers account {string} {double}€")
    public void accountTransaction(String kontoNameA, String kontoNameB, double betrag) {
        final Account a = accountMap.get(kontoNameA);
        final Account b = accountMap.get(kontoNameB);

        try {
            transaction.add(a.transaction(b, betrag));
            transactionStatus.add(true);
        } catch (Exception e) {
            transactionStatus.add(false);
        }
    }

    @Then("All transactions should {success}")
    public void allTransactionsFailOrSucceed(Boolean status) {
        if (status) {
            assertThat(transactionStatus).containsOnly(true);
        } else {
            assertThat(transactionStatus).containsOnly(false);
        }
    }

    @Then("Account {string} should have a balance of {double}€")
    public void checkAccountForBalance(String konto, double expectedBalance) {
        final double actualBalance = accountMap.get(konto).getBalance();
        assertThat(actualBalance)
                .as("Konto %s hat einen Kontostand von %s, sollte aber einen Kontostand von %s haben", konto, actualBalance, expectedBalance)
                .isEqualTo(expectedBalance, Offset.offset(0.01));
    }


    @And("transaction {int} should {success}")
    public void transactionFailure(int transaktionNum, Boolean status) {
        boolean transaktionErfolgt = this.transactionStatus.get(transaktionNum-1);
        if (status) {
            assertThat(transaktionErfolgt)
                    .as("Transaktion %s ist fehlgeschlagen", transaktionNum)
                    .isTrue();
        } else {
            assertThat(transaktionErfolgt)
                    .as("Transaktion %s ist nicht fehlgeschlagen", transaktionNum)
                    .isFalse();
        }
    }

    @Then("transaction {int} should be sent by {string}")
    public void transaktionValidateSender(int transaktionNum, String sender) {
        final Transaction transaction = this.transaction.get(transaktionNum - 1);
        final Account expectedSender = accountMap.get(sender);
        assertThat(transaction.getSender())
                .as("ActualSender=%s, ExpectedSender=%s", transaction.getSender(), expectedSender)
                .isEqualTo(expectedSender);
    }

    @And("transaction {int} should be received by {string}")
    public void transaktionValidateReceiver(int transactionNum, String receiver) {
        final Transaction transaction = this.transaction.get(transactionNum - 1);
        final Account expectedReceiver = accountMap.get(receiver);
        assertThat(transaction.getReceiver())
                .as("ActualSender=%s, ExpectedSender=%s", transaction.getSender(), expectedReceiver)
                .isEqualTo(expectedReceiver);
    }

    @And("transaction {int} should have a value of {double}€")
    public void transactionValidateAmount(int transactionNum, double value) {
        final Transaction transaction = this.transaction.get(transactionNum - 1);
        assertThat(transaction.getAmount())
                .isEqualTo(value, Offset.offset(0.01));
    }

    @ParameterType("fail|success|succeed")
    public Boolean success(String str) {
        return switch (str) {
            case "fail" -> false;
            case "success", "succeed" -> true;
            default -> throw new IllegalStateException("Unexpected value: " + str);
        };
    }
}
