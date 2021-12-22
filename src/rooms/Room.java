package rooms;

import people.Person;

import java.util.Calendar;

public abstract class Room implements Comparable<Room> {
    private static int ID = 0;
    protected final int id;
    protected Person tenant = null;
    protected final double volume;
    protected Calendar startDate, endDate;
    protected boolean rented = false;

    public abstract void clean();

    protected Room(double v) {
        id = ID++;
        volume = v;
    }
    protected Room(double h, double w, double l) {
        this(h * w * l);
    }

    public Person getTenant() {
        return tenant;
    }
    public int getId() {
        return id;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public boolean isRented() {
        return rented;
    }

    public void renew(int m) {
        endDate.add(Calendar.MONTH, m);
    }

    public void cancel() {
        this.clean();
        changeRentState();
        tenant.removeRoom(this.id);
        tenant = null;
        startDate = null;
        endDate = null;
    }

    public void changeRentState() {
        rented = !rented;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    public abstract void rent(Person p, int duration);

    @Override
    public int compareTo(Room o) {
        return Double.compare(this.volume, o.volume);
    }

    @Override
    public String toString() {
        return id + " | volume " + volume + " | tenant " + tenant.getFirstName() + " " +
                tenant.getLastName() + "\nstart date " + startDate.getTime() + "\nend date " + endDate.getTime() + "\n";
    }
}
