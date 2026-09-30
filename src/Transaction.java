public class Transaction {
    private User from;
    private User to;
    private Instrument instrumentFrom;
    private Instrument instrumentTo;
    private long amount;
    private TransactionStatus transactionStatus;

    public Transaction(User from, User to, Instrument instrumentFrom, Instrument instrumentTo, long amount, TransactionStatus transactionStatus) {
        this.from = from;
        this.to = to;
        this.instrumentFrom = instrumentFrom;
        this.instrumentTo = instrumentTo;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Instrument getInstrumentFrom() {
        return instrumentFrom;
    }

    public void setInstrumentFrom(Instrument instrumentFrom) {
        this.instrumentFrom = instrumentFrom;
    }

    public Instrument getInstrumentTo() {
        return instrumentTo;
    }

    public void setInstrumentTo(Instrument instrumentTo) {
        this.instrumentTo = instrumentTo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + from +
                ", to=" + to +
                ", instrumentFrom=" + instrumentFrom +
                ", instrumentTo=" + instrumentTo +
                ", amount=" + amount +
                ", transactionStatus=" + transactionStatus +
                '}';
    }
}
