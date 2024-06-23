Feature: Konto Überweisung

  Scenario: Transaction low
    Given Account "A" has a balance of 5.00€
      And Account "B" has a balance of 0.00€
    When Account "A" transfers account "B" 5.00€
    Then All transactions should success
      And Account "A" should have a balance of 0.00€
      And Account "B" should have a balance of 5.00€