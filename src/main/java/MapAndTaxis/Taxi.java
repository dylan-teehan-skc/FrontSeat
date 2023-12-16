package MapAndTaxis;
import java.util.Random;

public class Taxi {
    private static String taxitype;
    private static int peopleCarrying;
    private static int price;

    public void Taxi(String taxitype, int peopleCarrying, int price) {
        Taxi.taxitype = taxitype;
        Taxi.peopleCarrying = peopleCarrying;
        Taxi.price = price;
    }
    public void regNumber(){
    char firstLetter = generateRandomLetter();
    char secondLetter = generateRandomLetter();

    // Generate 4 random numbers
    int firstNumber = generateRandomNumber();
    int secondNumber = generateRandomNumber();
    int thirdNumber = generateRandomNumber();
    int fourthNumber = generateRandomNumber();

    // Combine letters and numbers to form the registration number
    String registrationNumber = String.format("%c%c%d%d%d%d", firstLetter, secondLetter,  firstNumber, secondNumber, thirdNumber, fourthNumber);

    // Print or use the registration number as needed
        System.out.println("Registration Number: " + registrationNumber);
}


    private char generateRandomLetter() {
        Random random = new Random();
        return (char) ('A' + random.nextInt(26)); // Generating a random uppercase letter
    }
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10); // Generating a random digit (0-9)
    }


    public static String getTaxitype() {
        return taxitype;
    }

    public static void setTaxitype(String taxitype) {
        Taxi.taxitype = taxitype;
    }

    public static int getPeopleCarrying() {
        return peopleCarrying;
    }

    public static void setPeopleCarrying(int peopleCarrying) {
        Taxi.peopleCarrying = peopleCarrying;
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        Taxi.price = price;
    }

    public void taxiInfo(){

    }
}
