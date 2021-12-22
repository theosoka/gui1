package items;

public class Amphibious extends Vehicle{
    protected int seatingCapacity;

    public Amphibious(double volume) {
        super(volume);
    }
    public Amphibious(double length, double width, double height) {
        super(length, width, height);
    }


    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }
}
