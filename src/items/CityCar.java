package items;

public class CityCar extends Vehicle{
    protected String color;

    public CityCar(double volume) {
        super(volume);
    }
    public CityCar(double length, double width, double height) {
        super(length, width, height);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
