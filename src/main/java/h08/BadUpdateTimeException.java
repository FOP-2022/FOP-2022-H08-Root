package h08;

import java.util.Calendar;

/**
 * class for h08.
 */
public class BadUpdateTimeException extends Exception {

    /**
     * normal constructor.
     *
     * @param calendar calendar
     * @param time calender too early or late
     */
    public BadUpdateTimeException(Calendar calendar, boolean time) {
        super((time ? "Update time is earlier than the last update: " : "Update time is in the future: ")
            + calendar.get(Calendar.DAY_OF_MONTH)
            + "." + (calendar.get(Calendar.MONTH) + 1)
            + "." + calendar.get(Calendar.YEAR)
            + " / " + calendar.get(Calendar.HOUR_OF_DAY)
            + ":" + calendar.get(Calendar.MINUTE)
            + ":" + calendar.get(Calendar.SECOND)
            + ":" + calendar.get(Calendar.MILLISECOND)
            + "!");
    }
}
