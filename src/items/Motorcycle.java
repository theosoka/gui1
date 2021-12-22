package items;

public class Motorcycle extends Vehicle{
    protected String fuelType;

    public Motorcycle(double volume) {
        super(volume);
    }
    public Motorcycle(double length, double width, double height) {
        super(length, width, height);
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }
}
