package items;

public class Item implements Comparable<Item> {
    protected String name;
    protected double volume;

    public Item(double volume) {
        this.volume = volume;
    }
    public Item(double length, double width, double height) {
        this(length * width * height);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public int compareTo(Item o) {
        if (Double.compare(o.volume, volume) == 0)
            return name.compareTo(o.name);
        return Double.compare(o.volume, volume);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }

}