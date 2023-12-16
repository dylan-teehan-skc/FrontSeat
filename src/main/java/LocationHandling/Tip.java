package LocationHandling;
import java.util.Scanner;


public class Tip extends Payment{
    private int updatedBalance;


    public void tip(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to leave a tip\n Press 1 for Yes \n Press 2 for No");
        int tip = scanner.nextInt();



       do {
           if (tip == 1) {
               System.out.println("Please enter how much you would like to tip");
               int tipAmount = scanner.nextInt();
               System.out.println("You tipped " + tipAmount);
               updatedBalance = (int)(getCurrentBalance() - tipAmount);
               System.out.println("Your balance is " + updatedBalance);


           } else if (tip == 2) {
               System.out.println("You choose not to tip\n Have a good day");
           } else {
               System.out.println("invalid input");
           }
       } while (tip <1 || tip > 2);

    }

}
