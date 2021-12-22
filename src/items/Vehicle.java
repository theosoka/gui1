package items;

public abstract class Vehicle extends Item {
    protected String vehicleType;
    protected String engineType;
    protected double engineCapacity;
    protected double weight;

    public Vehicle(double volume) {
        super(volume);
    }
    public Vehicle(double length, double width, double height) {
        this(length * width * height);
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getVehicleType() {
        return vehicleType;
    }
    public String getEngineType() {
        return engineType;
    }
    public double getEngineCapacity() {
        return engineCapacity;
    }
    public double getWeight() {
        return weight;
    }
}
