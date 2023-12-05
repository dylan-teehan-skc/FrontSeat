package MapAndTaxis;

public class TaxiDriver extends Person {
    private String reg;
    private int rating;
    private String carType;

    // Constructor for the TaxiDriver class
    public TaxiDriver(String name, String reg, int rating, String carType) {
        super(name);  // Using the constructor of the Person class
        this.reg = reg;
        this.rating = rating;
        this.carType = carType;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
