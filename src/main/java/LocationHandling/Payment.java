package LocationHandling;

import MapAndTaxis.TaxiDriving;

public class Payment extends PassengerCount{
    private double currentBalance = 50;
    private double taxiFee;

    public void proccessPayment(TaxiDriving taxi) {     // Method to process payment based on the provided TaxiDriving instance

        PassengerCount countPassengers1 =  new PassengerCount();

        double distance = taxi.getDistance(); //getting distance for TaxiDriving
        String formattedDistance = String.format("%.2f", distance); //formatting to two decimal
        System.out.println("You traveled " + formattedDistance + " km");


       taxiFee = (double) (distance *4 * (1 +(countPassengers1.passengersnumber * .1))); //adds fee based on passengers and distance
        String formattedTaxiFee = String.format("%.2f", taxiFee);
        System.out.println("Your taxi fee is " + formattedTaxiFee + "euro");
        updateBalance();

    }

    private void updateBalance() {
        currentBalance -= taxiFee;
        String formattedBalance = String.format("%.2f", currentBalance);
        System.out.println("Payment processed. Current Balance: " + formattedBalance);
        Review startReview = new Review();
        startReview.GiveReview();


    }
    public double getCurrentBalance() {
        return currentBalance;
    }


}