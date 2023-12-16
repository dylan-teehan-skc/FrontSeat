package MapAndTaxis;

public class TaxiDriver extends Person {
    private static String reg;
    private static int rating;
    private static String carType;

    // Constructor for the TaxiDriver class
    public TaxiDriver(String name, String reg, int rating, String carType) {
        super(name);  // Using the constructor of the Person class
        TaxiDriver.reg = reg;
        TaxiDriver.rating = rating;
        TaxiDriver.carType = carType;
    }

    public static String getReg() {
        return reg;
    }

    public static void setReg(String reg) {
        TaxiDriver.reg = reg;
    }

    public static int getRating() {
        return rating;
    }

    public static void setRating(int rating) {
        TaxiDriver.rating = rating;
    }

    public static String getCarType() {
        return carType;
    }

    public static void setCarType(String carType) {
        TaxiDriver.carType = carType;
    }
}
