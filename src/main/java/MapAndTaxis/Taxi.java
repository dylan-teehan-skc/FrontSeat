package MapAndTaxis;

public class Taxi {
    private static String taxitype;
    private static int peopleCarrying;
    private static int price;

    public void Taxi(String taxitype, int peopleCarrying, int price) {
        Taxi.taxitype = taxitype;
        Taxi.peopleCarrying = peopleCarrying;
        Taxi.price = price;
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
}
