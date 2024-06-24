Feature: Konto Überweisung

  Scenario: Transaction low
    Given Account "A" has a balance of 5.00€
      And Account "B" has a balance of 0.00€
    When Account "A" transfers account "B" 5.00€
    Then All transactions should success
      And Account "A" should have a balance of 0.00€
      And Account "B" should have a balance of 5.00€

  Scenario: Transaction odd
    Given Account "A" has a balance of 575.45€
      And Account "B" has a balance of 47.32€
    When Account "A" transfers account "B" 72.32€
    Then All transactions should success
      And Account "A" should have a balance of 503.13€
      And Account "B" should have a balance of 119.63€

  Scenario: Hopping Transaction
    Given Account "A" has a balance of 10.00€
      And Account "B" has a balance of 10.00€
      And Account "C" has a balance of 10.00€
    When Account "A" transfers account "B" 10.00€
      And Account "B" transfers account "C" 20.00€
    Then All transactions should succeed
      And Account "A" should have a balance of 0.00€
      And Account "B" should have a balance of 0.00€
      And Account "C" should have a balance of 30.00€

  Scenario: Fail Transaction
    Given Account "A" has a balance of 10.00€
      And Account "B" has a balance of 0.00€
    When Account "A" transfers account "B" 20.00€
    Then All transactions should fail
      And Account "A" should have a balance of 10.00€
      And Account "B" should have a balance of 0.00€

  Scenario: Success   And Failure Transactions
    Given Account "A" has a balance of 10.00€
      And Account "B" has a balance of 15.00€
      And Account "C" has a balance of 20.00€
      And Account "D" has a balance of 25.00€
    When Account "A" transfers account "B" 20.00€
      And Account "C" transfers account "A" 20.00€
      And Account "A" transfers account "B" 20.00€
      And Account "D" transfers account "A" 100.00€
    Then Account "A" should have a balance of 10.00€
      And Account "B" should have a balance of 35.00€
      And Account "C" should have a balance of 0.00€
      And Account "D" should have a balance of 25.00€
      And transaction 1 should fail
      And transaction 2 should succeed
      And transaction 3 should succeed
      And transaction 4 should fail

  Scenario: Validate Transactions
    Given Account "A" has a balance of 5.00€
      And Account "B" has a balance of 0.00€
    When Account "A" transfers account "B" 5.00€
    Then transaction 1 should be sent by "A"
      And transaction 1 should be received by "B"
      And transaction 1 should have a value of 5.00€

