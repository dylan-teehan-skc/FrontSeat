package LocationHandling;

import java.util.Scanner;

public class Review {

    public static void GiveReview() { //method to give review
        Scanner scanner = new Scanner(System.in);
        int rating; //stores rating

        do {
            System.out.println("Please review your taxi driver out of 10");
            rating = scanner.nextInt();

            if (rating < 0 || rating > 10) { //exception handling
                System.out.println("Invalid answer");
            } else {
                System.out.println("You gave your driver " + rating + " out of 10");
                Comment runComment = new Comment();
                runComment.askForComment();
            }
        } while (rating < 0 || rating > 10); //do while to make it so they have to give review

    }

}
