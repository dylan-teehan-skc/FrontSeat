// couldnt get this working yet



/*package LocationHandling;
public class Payment_NotWorking {
    private int currentBalance = 50;
    private double taxiFee;

    LocationHandling.PassengerCount passengers1 = new LocationHandling.PassengerCount();

    public void processMultiplier() {
        String numberOfPassengers = String.valueOf(passengers1.getPassengers());
        double multiplier;

        switch (numberOfPassengers) {
            case "1":
                multiplier = 1.1;
                break;
            case "2":
                multiplier = 1.2;
                break;
            case "3":
                multiplier = 1.3;
                break;
            case "4":
                multiplier = 1.4;
                break;
            case "5":
                multiplier = 1.5;
                break;
            case "6":
                multiplier = 1.6;
                break;
            case "7":
                multiplier = 1.7;
                break;
            default:
                multiplier = 1.0; // Default multiplier for 0 passengers or more than 7 passengers
                break;
        }

        processPayment(multiplier);
    }

    public void processPayment(double multiplier) {
        MapAndTaxis.TaxiDriving taxi = new MapAndTaxis.TaxiDriving(); // Assuming TaxiDriving has a getDistance() method
        double distance = taxi.getDistance();
        System.out.println("You traveled " + distance + " km");

        taxiFee = (double) (distance + 10 + multiplier);
        System.out.println("Your taxi fee is " + taxiFee);
        updateBalance();
    }

    private void updateBalance() {
        currentBalance -= taxiFee;
        System.out.println("Payment processed. Current Balance: " + currentBalance);
        Review startReview = new Review();
        startReview.GiveReview();
    }
}*/