package MapAndTaxis;
import java.util.Random;

public class Taxi {
    // Static fields are shared among all instances of the class
    private static String taxitype;
    private static int peopleCarrying;
    private static int price;
    public void taxi(String taxitype, int peopleCarrying, int price) {
        Taxi.taxitype = taxitype; // Initialize the static field taxitype
        Taxi.peopleCarrying = peopleCarrying; // Initialize the static field peopleCarrying
        Taxi.price = price; // Initialize the static field price
    }

    // Generate and print a random taxi registration number
    public void regNumber(){
        char firstLetter = generateRandomLetter();
        char secondLetter = generateRandomLetter();

        //Generate 4 random numbers
        int firstNumber = generateRandomNumber();
        int secondNumber = generateRandomNumber();
        int thirdNumber = generateRandomNumber();
        int fourthNumber = generateRandomNumber();

        //Combine letters and numbers to form the registration number
        String registrationNumber = String.format("%c%c%d%d%d%d", firstLetter, secondLetter,  firstNumber, secondNumber, thirdNumber, fourthNumber);

        // Print or use the registration number as needed
        System.out.println("Registration Number: " + registrationNumber);
    }

    // Generate a random uppercase letter
    private char generateRandomLetter() {
        Random random = new Random();
        return (char) ('A' + random.nextInt(26));
    }

    // Generate a random digit from 0-9
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }

    // Getter for taxitype
    public static String getTaxitype() {
        return taxitype;
    }

    // Setter for taxitype
    public static void setTaxitype(String taxitype) {
        Taxi.taxitype = taxitype;
    }

    // Getter for peopleCarrying
    public static int getPeopleCarrying() {
        return peopleCarrying;
    }

    // Setter for peopleCarrying
    public static void setPeopleCarrying(int peopleCarrying) {
        Taxi.peopleCarrying = peopleCarrying;
    }

    // Getter for price
    public static int getPrice() {
        return price;
    }

    // Setter for price
    public static void setPrice(int price) {
        Taxi.price = price;
    }

    // Placeholder method for displaying taxi information (implementation needed)
    public void taxiInfo(){

    }
}
