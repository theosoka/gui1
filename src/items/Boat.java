package items;

public class Boat extends Vehicle {
    protected String type;

    public Boat(double volume) {
        super(volume);
    }
    public Boat(double length, double width, double height) {
        super(length, width, height);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}