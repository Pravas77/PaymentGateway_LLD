import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello");

        BankAccount bankAccount1 = new BankAccount(10,500);
        CardTypeInstrument cardTypeInstrument1 = new CardTypeInstrument(bankAccount1,11,12, LocalDate.of(2026,1,1));
        UpiTypeInstrument upiTypeInstrument1 = new UpiTypeInstrument(bankAccount1,13);
        User user1 = new User(1, List.of(cardTypeInstrument1,upiTypeInstrument1));

        BankAccount bankAccount2 = new BankAccount(20,100);
        UpiTypeInstrument upiTypeInstrument2 = new UpiTypeInstrument(bankAccount2,23);
        User user2 = new User(2, List.of(upiTypeInstrument2));


        PaymentGateway paymentGateway = new PaymentGateway(List.of(user1,user2),new TransactionManager());


        Thread thread1 = new Thread(() -> {
            Credential credential = new Credential.Builder()
                    .setCardNo(11)
                    .setCvv(12)
                    .setExpiryDate(LocalDate.of(2026,1,1))
                    .build();

            System.out.println(paymentGateway.makePayment(user1,user2,cardTypeInstrument1,400,credential));
        });

        Thread thread2 = new Thread(() -> {
            Credential credential = new Credential.Builder()
                    .setPin(13)
                    .build();

            System.out.println(paymentGateway.makePayment(user1,user2,upiTypeInstrument1,200,credential));
        });


        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();
        System.out.println(bankAccount1);
        System.out.println(bankAccount2);

    }
}