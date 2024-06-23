# Feature Account Transactions
The implementation in `Account.java` needs to be verified. You as a Business Expert need to write the step/feature defintions.
Your colleague already defined the acceptance criteras! However it's seems like the developer missed some Step 
Definitions... With your background in computer science you can fix it by yourself!

## Acceptance Criteria
1. Transaction low balance:
   - Given account A has a balance of 5.00€ and account B has a balance of 0.00€. When account A transfers account B 5.00€. Then All transactions should success and account A should have a balance of 0.00€ and account B should have a balance of 5.00€
2. Transaction odd:
   - Given account A has a balance of 575.45€ and account B has a balance of 47.32€. When account A transfers account B 72.32€. Then All transactions should success and account A should have a balance of 503.13€ and account B should have a balance of 119.63€
3. Hopping Transaction:
   - Given account A has a balance of 10.00€ and account B has a balance of 10.00€ and account C has a balance of 10.00€. When account A transfers account B 10.00€ and account B transfers account C 20.00€. Then All transactions should fail and account A should have a balance of 0.00€ and account B should have a balance of 0.00€ and account C should have a balance of 30.00€
4. Fail Transaction:
   - Given account A has a balance of 10.00€ and account B has a balance of 0.00€. When account A transfers account B 20.00€. Then All transactions should fail and account A should have a balance of 10.00€ and account B should have a balance of 0.00€
5. Success and Failure Transactions:
   - Given account A has a balance of 10.00€ and account B has a balance of 15.00€ and account C has a balance of 20.00€ and account D has a balance of 25.00€. When account A transfers account B 20.00€ and account C transfers account A 20.00€ and account A transfers account B 20.00€ and account D transfers account A 100.00€. Then account A should have a balance of 10.00€ and account B should have a balance of 35.00€ and account C should have a balance of 0.00€ and account D should have a balance of 25.00€ and transaction 1 should succeed and transaction 2 should succeed and transaction 3 should succeed and transaction 4 should succeed
6. Validate Transactions:
   - Given account A has a balance of 5.00€ and account B has a balance of 0.00€. When account A transfers account B 5.00€. Then transaction 1 should be sent by A and transaction 1 should be received by B and transaction 1 should have a value of 5.00€

## Task 1:
Define the features according to the AC 2-6 in the file named `account.feature`. You can take AC1 as a reference.
Execute the Tests by clicking the 3 green buttons where the line numbers are

## Task 2:
Ups, the developer missed some crucial step definitions involved in AC6. You need to define them in 
`KontoStepDefintion.java`. If you install the cucumber plugin for intelliJ, you can create the step definitions within
the feature file!