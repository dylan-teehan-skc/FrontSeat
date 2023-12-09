package OisinPackage;

import java.util.Scanner;

import static MapAndTaxis.Car.taxiJourney;

public class Review {
    Scanner scanner = new Scanner(System.in);


    public void GiveReview() {
        System.out.println("Your taxi journey has begun");
        taxiJourney();
        Scanner scanner = new Scanner(System.in);
        int rating;


        do {

            // Sleep for 5 seconds
            try {
                Thread.sleep(11500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
