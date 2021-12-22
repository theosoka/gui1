package rooms;
import items.*;
import exceptions.*;
import people.Person;
import threads.TimeThread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ParkingSpace extends Room {
    protected ArrayList<Item> items = new ArrayList<>();
    double leftSpace = this.volume;

    public ParkingSpace(double h, double w, double l) {
        super(h, w, l);
    }
    public ParkingSpace(double v) {
        super(v);
    }

    public ArrayList<Item> getItems() {
        Collections.sort(items);
        return items;
    }
    public double getLeftSpace() {
        return leftSpace;
    }

    public void insertItem(Item item) throws TooManyThingsException {
        if (leftSpace - item.getVolume() < 0)
            throw new TooManyThingsException("Remove some old items to insert a new item");
        else
            items.add(item);
            leftSpace -= item.getVolume();
    }

    public void removeItem(int index) {
        if (index >= items.size()) {
            System.out.println("no such item");
            return;
        }
        leftSpace += items.get(index).getVolume();
        items.remove(index);
    }

    public void listItems() {
        if (items.size() == 0)
            System.out.println("no items");
        else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println('(' + i + ") " + items.get(i).getName() + " " + items.get(i).getVolume() + "m^3");
            }
        }
    }

    public void listItemsToFile(BufferedWriter bw) throws IOException {
        if (items.size() == 0)
            bw.write("no items.");
        else {
            for (int i = 0; i < items.size(); i++) {
                bw.write("(" + i + ") " + items.get(i).getName() + " " + items.get(i).getVolume() + "m^3\n");
            }
        }
    }

    @Override
    public void clean() {
        items.clear();
    }

    @Override
    public void rent(Person p, int duration) {
        if (p.getApartments().size() + p.getParkingSpaces().size() > 5) {
            System.out.println("too many rooms rented");
            return;
        }
        if (!p.check())
            return;
        p.rentParkingSpace(this);
        this.tenant = p;
        changeRentState();
        startDate = TimeThread.getDate();
        endDate = (Calendar)(startDate.clone());
        endDate.add(Calendar.MONTH, duration);
    }

    @Override
    public String toString() {
        return "PARKING SPACE ID " + super.toString();
    }


}
