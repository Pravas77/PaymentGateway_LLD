import java.time.LocalDate;

public class CardTypeInstrument extends Instrument {
    private long cardNo;
    private int cvv;
    private LocalDate expiryDate;

    public CardTypeInstrument(BankAccount bankAccount, long cardNo, int cvv, LocalDate expiryDate) {
        super(bankAccount);
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public long getCardNo() {
        return cardNo;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean validate(Credential credential) {
        return cardNo == credential.getCandNo() && cvv == credential.getCvv() && expiryDate.equals(credential.getExpiryDate());
    }

}
