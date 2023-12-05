import java.util.Scanner;
public class Review {
    Scanner scanner = new Scanner(System.in);


    public void GiveReview() {
        Scanner scanner = new Scanner(System.in);
        int rating;

        do {
            System.out.println("Please review your taxi driver out of 10");

            rating = scanner.nextInt();

            if (rating < 0 || rating > 10) {
                System.out.println("invalid answer");
            } else {

                System.out.println("You gave your driver " + rating + " out of 10");
            }
        } while (rating < 0 || rating > 10);
    }
}
