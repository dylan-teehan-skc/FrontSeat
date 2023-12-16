package LocationHandling;

import MapAndTaxis.TaxiDriving;

public class Payment {
    private int currentBalance = 50;
    private int taxiFee;

    public void proccessPayment(TaxiDriving taxi) {

        double distance = taxi.getDistance();
        String formattedDistance = String.format("%.2f", distance);
        System.out.println("You traveled " + formattedDistance + " km");

        taxiFee = (int) (distance * 2.5);
        System.out.println("Your taxi fee is " + taxiFee);
        updateBalance();

    }

    private void updateBalance() {
        currentBalance -= taxiFee;
        System.out.println("Payment processed. Current Balance: " + currentBalance);
        Review startReview = new Review();
        startReview.GiveReview();


    }
}


