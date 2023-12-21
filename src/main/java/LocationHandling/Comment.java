package LocationHandling;

import java.util.Scanner;


public class Comment {


    public void askForComment() {


        Scanner scanner = new Scanner(System.in); // creates new Scanner called scanner
        System.out.println("Would you like to leave a comment \n Type 1 for Yes \n Type 2 for No"); // prints to comand line
        int answer = scanner.nextInt(); // sets answer to the next int user types
        do {
            if (answer == 1) { // if else statement nested in do while loop
                System.out.println("Please leave your comment below");
                scanner.nextLine();
                String comment = scanner.nextLine();
                System.out.println("Thank you for your comment \n You commented " + comment);
                Tip runTip = new Tip();
                runTip.tip();
            } else if (answer == 2) {
                System.out.println("You decided not to leave a comment ");
                Tip runTip2 = new Tip(); //creates object Tip class
                runTip2.tip(); //runs tip
            } else {
                System.out.println("Invalid response");
            }

        } while (answer < 1 || answer > 2);
    }
}
