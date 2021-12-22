package rooms;
import people.Person;
import threads.TimeThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

public class Apartment extends Room {
    private LinkedList<Person> inhabitants = new LinkedList<>();

    public Apartment(double h, double w, double l) {
        super(h, w, l);
    }
    public Apartment(double v) {
        super(v);
    }

    public void addPerson(Person p) {
        if (inhabitants.size() > 0) {
             if (inhabitants.contains(p)) {
                System.out.println("this person already lives here");
                return;
            }
        }
        inhabitants.add(p);
    }

    public void removePerson(Person p) {
        if (this.tenant.equals(p)) {
            System.out.println("impossible to remove tenant");
            return;
        }
        if (!inhabitants.contains(p)) {
            System.out.println("this person doesn't live here");
            return;
        }
        inhabitants.remove(p);
    }

    public void listInhabitants() {
        for (Person p: inhabitants) {
            System.out.println(p.toString() + " tenant: " + this.tenant.equals(p));
        }
    }

    public void listInhabitantToFile(BufferedWriter bw) throws IOException {
        for (Person p: inhabitants) {
            bw.write(p.toString() + " tenant: " + this.tenant.equals(p) + "\n");
        }
    }

    @Override
    public void rent(Person p, int duration) {
        if (p.getApartments().size() + p.getParkingSpaces().size() > 5) {
            System.out.println("Too many apartments rented.");
            return;
        }
        p.rentApartment(this);
        this.tenant = p;
        changeRentState();
        inhabitants.add(p);
        startDate = TimeThread.getDate();
        endDate = (Calendar)(startDate.clone());
        endDate.add(Calendar.MONTH, duration);
    }

    @Override
    public void setTenant(Person tenant) {
        super.setTenant(tenant);
    }

    @Override
    public void clean() {
        inhabitants.clear();
    }

    @Override
    public String toString() {
        return "APARTMENT ID " + super.toString();
    }
}
