package LocationHandling;

import java.util.Scanner;


public class Tip extends Payment {


    private int tipBalance;


    public void tip() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to leave a tip\n Press 1 for Yes \n Press 2 for No");
        int tip = scanner.nextInt();


        do {
            if (tip == 1) {
                System.out.println("Please enter how much you would like to tip"); // prompts user to leave tip
                int tipAmount = scanner.nextInt(); //sets tipAmount to next int entered
                if (tipAmount > 10) {
                    System.out.println("You dont have enough money"); // checks if they have enough money
                } else {
                    System.out.println("You tipped " + tipAmount + " euro" + "\n Have a good day");
                    System.exit(0);
                }


            } else if (tip == 2) {
                System.out.println("You choose not to tip\n Have a good day");
                System.exit(0);
            } else {
                System.out.println("invalid input");
            }
        } while (tip < 1 || tip > 2); //repeat the loop
    }
}