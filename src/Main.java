import exceptions.TooManyThingsException;
import items.Item;
import people.Person;
import rooms.*;
import threads.TimeThread;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws TooManyThingsException {
        new Data();
        Scanner scan = new Scanner(System.in);
        ArrayList<Person> people = Data.people;
        ArrayList<Room> rooms = Data.rooms;
        Map<String, Person> peopleDB = Data.peopleDB;
        HousingEstate estate = new HousingEstate(rooms);


        rooms.get(0).rent(people.get(1), 1);
        rooms.get(2).rent(people.get(1), 1);
        Item bicycle = new Item(5);
        bicycle.setName("bicycle");
        people.get(1).getParkingSpaces().get(0).insertItem(bicycle);

        Runnable issues = new RentalIssues();
        Thread rentalIssues = new Thread(issues);
        rentalIssues.start();
        Thread time = new TimeThread();
        time.start();


        while (true) {
            Menu.flag = true;
            System.out.println("> to start using the application enter your PESEL");
            String pesel = scan.next();
            Person user = peopleDB.get(pesel);
            System.out.printf("> welcome, %s %s!\n> enter a number of an operation you want to do\n\n", user.getFirstName(), user.getLastName());
            Menu.setEstate(estate);
            Menu.setUser(user);
            while (Menu.flag) {
                Menu.menu();
            }
        }
    }

}


