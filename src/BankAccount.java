import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long balance;
    private long accountNo;
    private ReentrantLock lock;

    public BankAccount(int accountNo, int balance) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", accountNo=" + accountNo +
                '}';
    }
}
