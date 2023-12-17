package LocationHandling;

import MapAndTaxis.TaxiDriving;



public class Payment extends PassengerCount{
    private double currentBalance = 50;
    private double taxiFee;










    public void proccessPayment(TaxiDriving taxi) {
        PassengerCount countPassengers1 =  new PassengerCount();



        double distance = taxi.getDistance();
        String formattedDistance = String.format("%.2f", distance);
        System.out.println("You traveled " + formattedDistance + " km");


       // taxiFee = (double) (distance *4 * (1 +(countPassengers1.passengersnumber * .1)));
        System.out.println("Your taxi fee is " + taxiFee);
        updateBalance();

    }

    private void updateBalance() {
        currentBalance -= taxiFee;
        System.out.println("Payment processed. Current Balance: " + currentBalance);
        Review startReview = new Review();
        startReview.GiveReview();


    }
    public double getCurrentBalance() {
        return currentBalance;
    }


}