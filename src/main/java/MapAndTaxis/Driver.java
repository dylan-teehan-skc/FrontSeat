package MapAndTaxis;
public class Driver extends Person {
    private String reg2;
    private int rating2;
    private String carType2;

    //Constructor for the TaxiDriver2 class
    public Driver(String name, String reg, int rating, String carType) {
        super(name);  // Using the constructor of the Person class
        this.reg2 = reg;
        this.rating2 = rating;
        this.carType2 = carType;
    }

    public String getReg2() {
        return reg2;
    }

    public void setReg2(String reg2) {
        this.reg2 = reg2;
    }

    public int getRating2() {
        return rating2;
    }

    public void setRating2(int rating2) {
        this.rating2 = rating2;
    }

    public String getCarType2() {
        return carType2;
    }

    public void setCarType2(String carType2) {
        this.carType2 = carType2;
    }

}
