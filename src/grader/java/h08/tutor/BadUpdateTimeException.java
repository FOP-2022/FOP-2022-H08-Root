package h08.tutor;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Calendar;

/**
 * class for h08.
 */
@TestForSubmission("h08")
public class BadUpdateTimeException extends h08.BadUpdateTimeException {
    private final Calendar calendar;
    private final boolean time;

    public BadUpdateTimeException(Calendar calendar, boolean time) {
        super(calendar, time);
        this.calendar = calendar;
        this.time = time;
    }

    /**
     * get Message.
     *
     * @return message
     */
    public String getMessage() {
        return (time ? "Update time is earlier than the last update: " : "Update time is in the future: ")
            + calendar.get(Calendar.DAY_OF_MONTH)
            + "." + (calendar.get(Calendar.MONTH) + 1)
            + "." + calendar.get(Calendar.YEAR)
            + " / " + calendar.get(Calendar.HOUR_OF_DAY)
            + ":" + calendar.get(Calendar.MINUTE)
            + ":" + calendar.get(Calendar.SECOND)
            + ":" + calendar.get(Calendar.MILLISECOND)
            + "!";
    }
}
