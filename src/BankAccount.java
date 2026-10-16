import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long accountNo;
    private long balance;
    private ReentrantLock lock;

    public BankAccount(int accountNo, int balance) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public long getAccountNo() {
        return accountNo;
    }

    public long getBalance() {
        return balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "balance=" + balance + ", accountNo=" + accountNo + '}';
    }
}
