import people.Person;
import rooms.Apartment;
import rooms.ParkingSpace;
import rooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    public static ArrayList<Person> people = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    static Map<String, Person> peopleDB = new HashMap<>();

    Data() {
        people.add(new Person("John", "Smith", "12345678909", "Koszykowa 86"));
        people.add(new Person("Marie", "Curie", "09876543212", "Poznanska 7"));
        people.add(new Person("Yulia", "Adamczuk", "45678909123", "Rogalinska 67"));
        people.add(new Person("Kate", "Austen", "84612093546", "Krakowska 55"));
        people.add(new Person("Benjamin", "Linus", "57392746584", "Gorczewska 11"));
        people.add(new Person("Kasia", "Jakowska", "74537621904", "Al. Niepodleglosci 109"));
        people.add(new Person("Julia", "Kabanosy", "74837671904", "Al. Hot Bitches 10"));

        rooms.add(new Apartment(50));
        rooms.add(new Apartment(10, 12.5, 2.7));
        rooms.add(new ParkingSpace(4, 10, 3));
        rooms.add(new Apartment(30.5));
        rooms.add(new ParkingSpace(20));
        rooms.add(new Apartment(10, 10, 2.8));
        rooms.add(new Apartment(35, 60, 3));
        rooms.add(new Apartment(100));
        rooms.add(new ParkingSpace(20, 20, 2.5));
        rooms.add(new ParkingSpace(32, 10, 2.7));
        rooms.add(new ParkingSpace(30.7));
        rooms.add(new Apartment(45));

        for (Person p : people)
            peopleDB.put(p.getPeselNumber(), p);
    }
}
