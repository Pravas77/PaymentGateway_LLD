# Payment Gateway – Low Level Design

A thread-safe payment gateway in Java for transferring money between users with different payment instruments (Card and UPI). It validates credentials, debits and credits bank accounts atomically, and records every transaction with its status.

## Features
- Users with multiple payment instruments; the first one is the default for receiving money
- **Card** payments validated with card number, CVV and expiry date
- **UPI** payments validated with a PIN
- Insufficient-balance check
- Transaction history with `PENDING` → `SUCCESS` / `FAILED` status
- Safe concurrent payments, with no deadlock and no lost updates

## Design

| Class | Responsibility |
|---|---|
| `PaymentGateway` | Entry point: validates the sender's credential and starts the transfer to the receiver's default instrument |
| `TransactionManager` | Locks both accounts, checks balance, debits, credits and stores the transaction |
| `Instrument` | Abstract payment method linked to a `BankAccount`, with an abstract `validate(credential)` |
| `CardTypeInstrument` | Validates card number, CVV and expiry |
| `UpiTypeInstrument` | Validates UPI PIN |
| `Credential` | Credentials supplied for a payment, built with a **Builder** |
| `BankAccount` | Balance plus a `ReentrantLock` |
| `Transaction`, `TransactionStatus` | Payment record and its state |
| `User` | Holds a list of instruments |

```
makePayment(from, to, instrument, amount, credential)
   │ validate credential (Card / UPI)
   ▼
TransactionManager
   │ lock both accounts in account-number order
   │ check balance → debit sender → credit receiver
   ▼
Transaction(SUCCESS / FAILED)
```

## Design patterns
- **Strategy / Polymorphism** – each instrument validates its own credential, so adding Net Banking or a Wallet is a new `Instrument` subclass.
- **Builder** – `Credential` has different required fields for Card and UPI; the builder sets only the fields a payment needs.

## Concurrency
- Each `BankAccount` has its own `ReentrantLock`, and a transfer holds **both** accounts' locks, so the debit and credit happen as one atomic step.
- **Deadlock prevention:** locks are always taken in ascending **account-number order**. If A pays B while B pays A at the same time, both threads lock the lower account first, so they can never wait on each other in a cycle.
- Locks are released in a `finally` block, even when a payment fails.
- Transactions are stored in a `CopyOnWriteArrayList`.

## How to run
Requires Java 17+.
```bash
javac -d out src/*.java
java -cp out Main
```
`Main` gives User 1 an account with ₹500 and both a card and a UPI instrument. Two threads pay User 2 at the same time: ₹400 by card and ₹200 by UPI from the same account. One payment succeeds and the other fails with insufficient balance. The final balances are then printed.

## Possible extensions
- Idempotency key, so a retried request is not charged twice
- Refunds and transaction reversal
- Fees per instrument type, and storing only masked card numbers
