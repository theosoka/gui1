package people;

import exceptions.ProblematicTenantException;
import rooms.Apartment;
import rooms.ParkingSpace;
import rooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Person implements HousingEstateActions {
    protected ArrayList<ParkingSpace> parkingSpaces = new ArrayList<>();
    protected ArrayList<Apartment> apartments = new ArrayList<>();
    protected Map<Integer, TenantLetter> letterMap = new HashMap<>();
    protected String peselNumber;
    private final String firstName;
    private final String lastName;
    private final String address;

    public Person(String name, String surname, String pesel, String address) {
        firstName = name;
        lastName = surname;
        peselNumber = pesel;
        this.address = address;
    }

    public String getPeselNumber() {
        return peselNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public ArrayList<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }
    public ArrayList<Apartment> getApartments() {
        return apartments;
    }
    public Map<Integer, TenantLetter> getLetterMap() {
        return letterMap;
    }
    public String getAddress() {
        return address;
    }

    public boolean ifLetterExists(int id) {
        return letterMap.containsKey(id);
    }
    public void removeLetter(Room r) {
        letterMap.remove(r.getId());
    }

    public boolean check() {
        if (letterMap.size() >= 3) {
            throw new ProblematicTenantException(this);
        }
        if (apartments.size() + parkingSpaces.size() > 5) {
            System.out.println("Too many apartments rented.");
            return false;
        }
        return true;
    }

    @Override
    public void removeRoom(int id) {
        for (int i =0; i < apartments.size(); ++i) {
            if (apartments.get(i).getId() == id)
                apartments.remove(i);
        }
        for (int i =0; i < parkingSpaces.size(); ++i) {
            if (parkingSpaces.get(i).getId() == id)
                parkingSpaces.remove(i);
        }
    }

    @Override
    public void showRented() {
        if (apartments.size() == 0)
            System.out.println("you are not renting any apartments");
        else {
            for (Apartment a : apartments) {
                System.out.println("APARTMENTS\n");
                System.out.println(a);
            }
        }
        if (parkingSpaces.size() == 0)
            System.out.println("you are not renting any parking spaces");
        else {
            for (ParkingSpace p : parkingSpaces) {
                System.out.println("PARKING SPACES\n");
                System.out.println(p);
            }
        }
    }

    @Override
    public void rentApartment(Apartment apt) {
        apartments.add(apt);
    }

    @Override
    public void rentParkingSpace(ParkingSpace ps) {
        parkingSpaces.add(ps);
    }

    @Override
    public void checkIn(Person p, Apartment a) {
        if (apartments.contains(a))
            a.addPerson(p);
    }

    @Override
    public void checkOut(Person p, Apartment a) {
        if (apartments.contains(a))
            a.removePerson(p);
    }

    @Override
    public void addTenantLetter(TenantLetter letter) {
        letterMap.put(letter.ap.getId(), letter);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " +
                "pesel: " + peselNumber + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(peselNumber, person.peselNumber);

    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSpaces, apartments, letterMap, peselNumber, firstName, lastName, address);
    }
}
