package MapAndTaxis;

public class Taxi {
    private Taxi taxitype;
    private int peopleCarrying;
    private int price;

    public void Taxi(Taxi taxitype, int peopleCarrying, int price){
        this.taxitype = taxitype;
        this.peopleCarrying = peopleCarrying;
        this.price = price;
    }

    public Taxi getTaxitype() {
        return taxitype;
    }

    public void setTaxitype(Taxi taxitype) {
        this.taxitype = taxitype;
    }

    public int getPeopleCarrying() {
        return peopleCarrying;
    }

    public void setPeopleCarrying(int peopleCarrying) {
        this.peopleCarrying = peopleCarrying;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
