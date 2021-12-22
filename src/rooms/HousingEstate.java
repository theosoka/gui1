package rooms;
import java.util.*;

public class HousingEstate {
    protected ArrayList<Room> rooms;
   protected Map<Integer, Room> housingEstate = new HashMap<>();

    public HousingEstate(ArrayList<Room> rooms) {
        this.rooms = rooms;
        for (Room r: rooms) {
            housingEstate.put(r.getId(), r);
        }
    }

    public void showAvailableApartments() {
        System.out.println("AVAILABLE APARTMENTS:");
        for (Room r: rooms) {
            if (!r.isRented() && (r instanceof Apartment))
                System.out.printf("APARTMENT ID %d volume = %s\n", r.getId(), r.volume);
        }
    }
    public void showAvailableParkingSpaces() {
        for (Room r: rooms) {
            if (!r.isRented() && (r instanceof ParkingSpace))
                System.out.printf("PARKING SPACE ID %d volume = %s\n", r.getId(), r.volume);
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public Map<Integer, Room> getHE() {
        return housingEstate;
    }
}

