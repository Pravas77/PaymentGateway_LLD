public abstract class Instrument {
    private BankAccount bankAccount;

    public Instrument(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public abstract boolean validate(Credential credential);

    @Override
    public String toString() {
        return bankAccount.toString();
    }
}
