package people;

import rooms.Apartment;
import rooms.ParkingSpace;
import rooms.Room;

public interface HousingEstateActions {

    void rentApartment(Apartment apt);
    void rentParkingSpace(ParkingSpace ps);
    void checkIn(Person p, Apartment a);
    void checkOut(Person p, Apartment a);
    void addTenantLetter(TenantLetter letter);
    void showRented();
    void removeRoom(int id);
    boolean check();

}
