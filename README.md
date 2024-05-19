# JavaCodeChallenge
# Banking Application

## Overview
This application provides a simple banking system with RESTful API to manage bank accounts and transactions.

## How to Run
- Clone this repository.
- Run `BankingApplication.java` as a Spring Boot application.

## API Endpoints
**Test via Postman:**

## 1. Create a Bank with All Required Values
   ### Endpoint: POST /api/banks
   **Payload:**
   ```json
   {
   "name": "Example Bank",
   "transactionFlatFeeAmount": 10.00,
   "transactionPercentFeeValue": 0.05
   }
   ```
   Postman Action: Use the POST method at the endpoint with the above JSON payload to create a new bank.

## 2. Create an Account
   ### Endpoint: POST /api/accounts
   **Payload:**
   ```json
   {
   "userName": "John Doe",
   "balance": 500.00,
   "bankId": 1
   }
   ```

Postman Action: Use the POST method at the endpoint with the JSON payload to create a new account.

## 3. Perform Both Flat Fee and Percent Fee Transaction
   ### Endpoint: POST /api/transactions/transfer
   **Payload for Flat Fee:**
   ```json
   {
   "fromAccountId": 1,
   "toAccountId": 2,
   "amount": 100.00,
   "isFlatFee": true,
   "feeValue": 10.00
   }
   ```
   **Payload for Percent Fee:**

   ```json
   {
   "fromAccountId": 1,
   "toAccountId": 2,
   "amount": 100.00,
   "isFlatFee": false,
   "feeValue": 0.05
   }
   ```
   Postman Action: Use the POST method at the endpoint with either payload to perform transactions.

## 4. Withdraw and Deposit Money to an Account
   ### Withdraw Endpoint: POST /api/transactions/withdraw
   ### Deposit Endpoint: POST /api/transactions/deposit
   **Withdraw Payload:**
   ```json
   {
   "accountId": 1,
   "amount": 50.00,
   "reason": "Withdraw"
   }
   ```

**Deposit Payload:**
```json
{
"accountId": 2,
"amount": 75.00,
"reason": "Deposit"
}
```
Postman Action: Use the POST method at each endpoint with the respective payload for withdrawing or depositing money.

## 5. See List of Transactions for Any Account
   ### Endpoint: GET /api/transactions/account/{accountId}

## 6. Check Account Balance for Any Account
   ### Endpoint: GET /api/accounts/{accountId}/balance

## 7. See List of Bank Accounts
   ### Endpoint: GET /api/banks/{id}/accounts

Postman Action: Use the GET method at the endpoint to retrieve all accounts by bank.

## 8. Check Bank Total Transaction Fee Amount 
(this is not finished)
   ### Endpoint: GET /api/banks/{bankId}/total-fees
   Postman Action: Use the GET method at the endpoint replacing {bankId} with the bank ID.

## 9. Check Bank Total Transfer Amount 
(this is not finished)
   ### Endpoint: GET /api/banks/{bankId}/total-transfers

### Notes
Ensure you have the required IDs (such as bankId and accountId) when performing operations that require them.
Make sure the bank and accounts are created before performing transactions, withdrawals, or deposits.

## Security
- No Security used.

## Database
- Uses H2 in-memory database for data persistence.
