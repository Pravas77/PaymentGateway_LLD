import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionManager {
    private List<Transaction> transactions;

    public TransactionManager() {
        this.transactions = new CopyOnWriteArrayList<>();
    }

    public Transaction makePayment(User from, User to, Instrument instrumentFrom, Instrument instrumentTo, long amount) {

        Transaction transaction = new Transaction(from, to, instrumentFrom, instrumentTo, amount, TransactionStatus.PENDING);
        List<BankAccount> bankAccounts;
        if (instrumentTo.getBankAccount().getAccountNo() < instrumentFrom.getBankAccount().getAccountNo())
            bankAccounts = List.of(instrumentTo.getBankAccount(), instrumentFrom.getBankAccount());
        else
            bankAccounts = List.of(instrumentFrom.getBankAccount(), instrumentTo.getBankAccount());


        List<ReentrantLock> locks = new ArrayList<>();
        try {


            for (BankAccount bankAccount : bankAccounts) {
                ReentrantLock lock = bankAccount.getLock();
                lock.lock();
                locks.add(lock);
            }


            // sender
            long senderBalance = instrumentFrom.getBankAccount().getBalance();
            if (senderBalance < amount) {
                transaction.setTransactionStatus(TransactionStatus.FAILED);
                transactions.add(transaction);
                throw new RuntimeException("Insufficient balance  :  " + transaction);
            }
            instrumentFrom.getBankAccount().setBalance(senderBalance - amount);


            //receiver
            long receiverBalance = instrumentTo.getBankAccount().getBalance();
            instrumentTo.getBankAccount().setBalance(receiverBalance + amount);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactions.add(transaction);
            return transaction;
        } finally {
            for (ReentrantLock lock : locks) lock.unlock();
        }


    }

}
