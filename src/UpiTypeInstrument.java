import java.util.Objects;

public class UpiTypeInstrument extends Instrument{
    private int pin;

    public UpiTypeInstrument(BankAccount bankAccount, int pin) {
        super(bankAccount);
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean validate(Credential credential){
        return pin == credential.getPin();
    }

}
