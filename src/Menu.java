import exceptions.TooManyThingsException;
import items.*;
import people.Person;
import rooms.*;
import threads.TimeThread;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public abstract class Menu {
    protected static Scanner scan = new Scanner(System.in);
    protected static Person user;
    protected static HousingEstate estate;
    protected static boolean flag = true;

    public static void setUser(Person user) {
        Menu.user = user;
    }
    public static void setEstate(HousingEstate estate) {
        Menu.estate = estate;
    }

    public static void menu() {
        System.out.println("""
                |                 MENU                |
                |1.My rented rooms                    |
                |2.Rent an apartment                  |
                |3.Check in/out people                |
                |4.Renew/cancel rent                  |
                |5.Add/remove item(s) to parking place|
                |6.Save status to the file            |
                |7.Log out                            |
                """);
        System.out.println("---- " + TimeThread.getDate().getTime() + " ----");
        int action = scan.nextInt();
        switch (action) {
            case 1 -> user.showRented();
            case 2 -> rentApMenu();
            case 3 -> checkInOutMenu();
            case 4 -> renewCancelMenu();
            case 5 -> addRemoveItemsMenu();
            case 6 -> saveToFile(user);
            case 7 -> flag = false;
        }
    }

    public static void rentApMenu() {
        estate.showAvailableApartments();
        System.out.println("""
                |enter an ID of an apartment you want to rent |
                |enter -1 if you want to rent a parking space |
                |enter any other negative integer to go back  |
                """);
        int action = scan.nextInt();
        if (action < -1) return;
        if (action == -1) rentPSMenu();
        if (!estate.getHE().containsKey(action)) {
            System.out.println("wrong ID. try again");
            menu();
        }
        Apartment ap = (Apartment) (estate.getHE().get(action));
        System.out.println("> write in months for how long you are renting the apartment");
        action = scan.nextInt();
        ap.rent(user, action);
        System.out.println("> type 1 if you would like to rent a parking space\n> type 0 to go back to menu");
        action = scan.nextInt();
        switch (action) {
            case 0 -> menu();
            case 1 -> rentPSMenu();
        }
    }

    public static void rentPSMenu() {
        estate.showAvailableParkingSpaces();
        System.out.println("""
                |enter an ID of an parking space you want to rent|
                |enter a negative number to go back to menu      |
                """);
        int action = scan.nextInt();
        if (action < 0)
            return;
        if (!estate.getHE().containsKey(action)) {
            System.out.println("! wrong ID. try again");
            rentPSMenu();
        }
        ParkingSpace ps = (ParkingSpace) (estate.getHE().get(action));
        System.out.println("> write in months for how long you are renting the parking space");
        action = scan.nextInt();
        ps.rent(user, action);
        System.out.println("""
                |type 0 to insert items  |
                |type 1 to go bak to menu|
                """);
        action = scan.nextInt();
        switch (action) {
            case 0 -> addRemoveItemsMenu();
            case 1 -> menu();
        }
    }

    public static void checkInOutMenu() {
        if (user.getApartments().size() == 0) {
            System.out.println("! you don't have apartments");
            menu();
        }
        user.showRented();
        System.out.println("""
                > enter an ID of an apartment in which you want to make changes
                > enter -1 to go back to menu                                                                
                """);
        int action = scan.nextInt();
        if (action == -1)
            menu();
        if (!(estate.getHE().get(action) instanceof Apartment)) {
            System.out.println("! choose an apartment");
            checkInOutMenu();
        }
        Apartment ap = (Apartment) (estate.getHE().get(action));
        System.out.println("""
                > enter 0 to check in a person 
                > enter 1 to check out a person
                > enter -1 to go back to menu                             
                """);
        action = scan.nextInt();
        switch (action) {
            case 0 -> checkIn(ap);
            case 1 -> checkOut(ap);
            case -1 -> menu();
        }

    }

    public static void checkIn(Apartment ap) {
        System.out.println("> enter PESEL of a person you want to add to the apartment");
        String pesel = scan.next();
        user.checkIn(Data.peopleDB.get(pesel), ap);
    }

    public static void checkOut(Apartment ap) {
        ap.listInhabitants();
        System.out.println("> enter PESEL of a person you want to remove from the apartment");
        String pesel = scan.next();
        user.checkOut(Data.peopleDB.get(pesel), ap);
    }

    public static void renewCancelMenu() {
        System.out.println("> enter 0 to go back to menu\n> type 1 to cancel rent\n> type 2 to renew rent");
        int action = scan.nextInt();
        switch (action) {
            case 0 -> menu();
            case 1 -> cancel();
            case 2 -> renew();
        }
    }

    public static void renew() {
        user.showRented();
        System.out.println("> enter an ID of a room rent of which you want to renew");
        int action = scan.nextInt();
        if (!estate.getHE().containsKey(action) || estate.getHE().get(action).getTenant() != user) {
            System.out.println("! wrong ID or it is not your room");
            menu();
        }
        System.out.println("> enter amount of months you want to edd to the end date");
        int months = scan.nextInt();
        estate.getHE().get(action).renew(months);
        if (user.ifLetterExists(estate.getHE().get(action).getId())) {
            user.removeLetter(estate.getHE().get(action));
        }
    }

    public static void cancel() {
        user.showRented();
        System.out.println("> enter an ID of a room you want to stop renting");
        int action = scan.nextInt();
        if (!estate.getHE().containsKey(action) || estate.getHE().get(action).getTenant() != user) {
            System.out.println("! wrong ID or it is not your room");
            menu();
        }
        estate.getHE().get(action).cancel();
        if (user.ifLetterExists(estate.getHE().get(action).getId())) {
            user.removeLetter(estate.getHE().get(action));
        }
    }

    public static void addRemoveItemsMenu() {
        if (user.getParkingSpaces().size() == 0) {
            System.out.println("! you don't have any parking spaces");
            menu();
        }
        System.out.println("""
                > enter 0 to remove items   
                > enter 1 to insert items   
                > enter 2 to go back to menu
                """);
        int action = scan.nextInt();
        System.out.println("> enter an ID of a parking space which you want to modify");
        user.showRented();
        int id = scan.nextInt();
        if (estate.getHE().get(id) instanceof Apartment) {
            System.out.println("choose a parking space");
            menu();
            return;
        }
        ParkingSpace ps = (ParkingSpace) (estate.getHE().get(id));
        switch (action) {
            case 0 -> {
                if (ps.getItems().size() == 0) {
                    System.out.println("! nothing to remove. try again");
                    addRemoveItemsMenu();
                }
                ps.listItems();
                System.out.println("> enter an index of an item you wish to remove");
                action = scan.nextInt();
                ps.removeItem(action);
            }
            case 1 -> insertItemMenu(ps);
            case 2 -> menu();
        }

    }

    public static void insertItemMenu(ParkingSpace ps) {
        System.out.println("""
                        specify what item you want to insert.
                        if it is a vehicle start with a word 'vehicle' then write the name of the vehicle. available types:
                            * amphibious 
                            * boat
                            * cityCar
                            * motorcycle
                            * offRoadCar
                        """);
        String item = scan.nextLine();
        item = scan.nextLine();
        String[] itemInfo = item.split(" ");
        Item itemInsert;
        if (itemInfo[0].equals("vehicle")) {
            if (itemInfo.length == 5)
                itemInsert = createVehicle(itemInfo[1], new double[]{Double.parseDouble(itemInfo[2]), Double.parseDouble(itemInfo[3]), Double.parseDouble(itemInfo[4])}, 3);
            else
                itemInsert = createVehicle(itemInfo[1], new double[]{Double.parseDouble(itemInfo[2])}, 1);
        } else {
            if (itemInfo.length == 2)
                itemInsert = new Item(Double.parseDouble(itemInfo[1])) {
                };
            else
                itemInsert = new Item(Double.parseDouble(itemInfo[1]), Double.parseDouble(itemInfo[2]), Double.parseDouble(itemInfo[3])) {
                };
            itemInsert.setName(itemInfo[0]);
        }
        try {
            ps.insertItem(itemInsert);
        }
        catch (TooManyThingsException e) {
            System.out.println(e.toString());
            menu();
        }
    }

    public static void saveToFile(Person p) {
        try (
                BufferedWriter bw =
                        Files.newBufferedWriter(
                                Paths.get("D:\\it\\gui-project-1\\src\\result.txt"), UTF_8)
        ) {
            bw.write(p.toString());
            Collections.sort(p.getApartments());
            Collections.sort(p.getParkingSpaces());
            for (Room a: p.getApartments()) {
                bw.write(a.toString());
                bw.write("\n");
                bw.write("\nINHABITANTS");
                ((Apartment)a).listInhabitantToFile(bw);
                bw.write("\n");
            }
            for (ParkingSpace a: p.getParkingSpaces()) {
                bw.write(a.toString());
                a.listItemsToFile(bw);
                bw.write("\n");
            }

        } catch (IOException e) {
            System.out.println("something is wrong");
        }
    }

    public static Vehicle createVehicle(String name, double[] v, int size) {
        switch (name.toLowerCase()) {
            case "offroadcar" -> {
                if (size == 3)
                    return new OffRoadCar(v[0], v[1], v[2]);
                else
                    return new OffRoadCar(v[0]);
            }
            case "boat" -> {
                if (size == 3)
                    return new Boat(v[0], v[1], v[2]);
                else
                    return new Boat(v[0]);
            }
            case "citycar" -> {
                if (size == 3)
                    return new CityCar(v[0], v[1], v[2]);
                else {
                    return new CityCar(v[0]);
                }
            }
            case "amphibious" -> {
                if (size == 3)
                    return new Amphibious(v[0], v[1], v[2]);
                else
                    return new Amphibious(v[0]);
            }
            case "motorcycle" -> {
                if (size == 3)
                    return new Motorcycle(v[0], v[1], v[2]);
                else
                    return new Motorcycle(v[0]);
            }
            default -> {
                System.out.println("try again");
                return new CityCar(20);
            }
        }

    }

}
