package LocationHandling;
import java.util.Scanner;


public class Comment {





    public void askForComment() {


    Scanner scanner = new Scanner(System.in);
    System.out.println("Would you like to leave a comment \n Type 1 for Yes \n Type 2 for No");
    int answer = scanner.nextInt();
    do{
        if(answer == 1) {
            System.out.println("Please leave your comment below");
            scanner.nextLine();
            String comment = scanner.nextLine();
            System.out.println("Thank you for your comment \n You commented " + comment + " \n Have a nice day");
            Tip runTip = new Tip();
            runTip.tip();
        } else if (answer == 2) {

            System.out.println("You decided not to leave a comment \n Have a nice day");

            Tip runTip2 = new Tip();
            runTip2.tip();
        }else {

                System.out.println("Invalid responcse");
        }


    }while( answer <1 || answer >2);



}


}
