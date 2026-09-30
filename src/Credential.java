import java.time.LocalDate;

public class Credential {
    private long cardNo;
    private int cvv;
    private LocalDate expiryDate;
    private int pin;

    public Credential(Builder builder) {
        this.cardNo = builder.cardNo;
        this.cvv = builder.cvv;
        this.expiryDate = builder.expiryDate;
        this.pin = builder.pin;
    }

    public long getCardNo() {
        return cardNo;
    }

    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getCandNo() {
        return cardNo;
    }


    public static class Builder{
        private long cardNo;
        private int cvv;
        private LocalDate expiryDate;
        private int pin;

        public Builder setCardNo(long cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public Builder setCvv(int cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder setExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder setPin(int pin) {
            this.pin = pin;
            return this;
        }

        public Credential build(){
            return new Credential(this);
        }
    }
}
