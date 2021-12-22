package threads;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeThread extends Thread {
     static Calendar date = new GregorianCalendar();

    @Override
    public void run() {
        date.set(2021, Calendar.APRIL, 22);
        while (true) {
            try {
                Thread.sleep(5000);
                date.add(Calendar.DATE, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Calendar getDate() {
        return date;
    }
}
