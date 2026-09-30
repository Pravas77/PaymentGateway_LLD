import java.util.List;

public class PaymentGateway {
    private List<User> users;
    private TransactionManager transactionManager;

    public PaymentGateway(List<User> users, TransactionManager transactionManager) {
        this.users = users;
        this.transactionManager = transactionManager;
    }

    public Transaction makePayment(User from, User to, Instrument instrumentFrom, int amount, Credential credential) {

        if (!instrumentFrom.validate(credential)) {
            throw new RuntimeException("Invalid credential");
        }
        Instrument instrumentTo = to.getDefaultInstrument();

        return transactionManager.makePayment(from, to, instrumentFrom, instrumentTo, amount);
    }
}
