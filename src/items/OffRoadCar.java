package items;

public class OffRoadCar extends Vehicle{
    protected String tireType;

    public OffRoadCar(double volume) {
        super(volume);
    }
    public OffRoadCar( double length, double width, double height) {
        this( length * width * height);
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public String getTireType() {
        return tireType;
    }
}
