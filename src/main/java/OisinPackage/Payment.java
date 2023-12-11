/*package OisinPackage;

public class Payment {
    private int currentBalance = 20;
    private int taxiFee;

    public void calculatePayment(TaxiMap taxiMap) {
        int initialX = taxiMap.getPlayerX();
        int initialY = taxiMap.getPlayerY();
        int finalX = taxiMap.getPlayerFinalX();
        int finalY = taxiMap.getPlayerFinalY();

        int distance = Math.abs(finalX - initialX) + Math.abs(finalY - initialY);
        taxiFee = distance * 2;
        currentBalance -= taxiFee;

        System.out.println("You traveled " + distance + " blocks. The total taxi fee is: $" + taxiFee);
        System.out.println("Current Balance: $" + currentBalance);
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}*/