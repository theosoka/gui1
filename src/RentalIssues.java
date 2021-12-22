import people.TenantLetter;
import rooms.Room;
import threads.TimeThread;

import java.time.Duration;
import java.util.ArrayList;

public class RentalIssues implements Runnable {
    protected ArrayList<Room> rooms = Data.rooms;

//    public RentalIssues(ArrayList<Room> rooms) {
//        this.rooms = rooms;
//    }

    @Override
    public void run() {
        while (true) {
            for (Room r : rooms) {
                if (r.isRented()) {
                    if (r.getEndDate().compareTo(TimeThread.getDate()) < 0) {
                        int days = (int) Duration.between(r.getEndDate().toInstant(), TimeThread.getDate().toInstant()).toDays();
                        if (!r.getTenant().ifLetterExists(r.getId()))
                            r.getTenant().addTenantLetter(new TenantLetter(r.getTenant(), r));
                        if (days > 30) {
                            r.cancel();
                        }
                    }
                }
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
