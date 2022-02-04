package h08.tutor;

import h08.TimeStamp;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Calendar;
import java.util.GregorianCalendar;
@TestForSubmission("h08")
public class TimeStamp2 extends TimeStamp {
    private Calendar lastUpdate;

    public TimeStamp2() {
        update();
    }

    /**
     * this method sets the variable lastUpdate to a Calendar Object which represents the current time
     */
    public void update() {
        lastUpdate = new GregorianCalendar();
    }

    /**
     * this method returns the lastUpdate Calendar object
     *
     * @return the lastUpdate Calendar object
     */
    public Calendar getTimeStamp() {
        return lastUpdate;
    }

    /**
     * this method sets the lastUpdate variable to the parameter calendar if: calendar is not before the current lastUpdate object and calendar is not after the current time
     *
     * @param calendar potential new lastUpdate value, if conditions are met
     */
    public void update(Calendar calendar) {
        assert !calendar.before(lastUpdate) && !calendar.after(Calendar.getInstance());

        lastUpdate = calendar;
    }

    /**
     * this method checks whether the new calendar older than the current calendar or in the future
     *
     * @param calendar the potential new one
     * @throws h08.UpdateTimeBeforeLastUpdateException thrown if the new calendar is older than the current
     * @throws h08.UpdateTimeInTheFutureException      thrown if the new calendar is in the future
     */
    public void updateWithExc1(Calendar calendar)
        throws UpdateTimeBeforeLastUpdateException, UpdateTimeInTheFutureException {
        if (calendar.before(lastUpdate)) {
            throw new UpdateTimeBeforeLastUpdateException(calendar);
        }

        if (calendar.after(Calendar.getInstance())) {
            throw new UpdateTimeInTheFutureException(calendar);
        }

        lastUpdate = calendar;
    }

    /**
     * this method checks whether the new calendar older than the current calendar or in the future
     *
     * @param calendar the potential new one
     * @throws h08.BadUpdateTimeException thrown if calendar is older than the current one or in the future
     */
    public void updateWithExc2(Calendar calendar) throws h08.BadUpdateTimeException {
        if (calendar.before(lastUpdate)) {
            throw new UpdateTimeBeforeLastUpdateException(calendar);
        }

        if (calendar.after(Calendar.getInstance())) {
            throw new UpdateTimeInTheFutureException(calendar);
        }

        lastUpdate = calendar;
    }

    /**
     * this method checks whether the new calendar older than the current calendar or in the future
     *
     * @param calendar the potential new one
     * @throws Exception thrown if calendar is older than the current one or in the future
     */
    public void updateWithExc3(Calendar calendar) throws Exception {
        if (calendar.before(lastUpdate)) {
            throw new UpdateTimeBeforeLastUpdateException(calendar);
        }

        if (calendar.after(Calendar.getInstance())) {
            throw new UpdateTimeInTheFutureException(calendar);
        }

        lastUpdate = calendar;
    }

    /**
     * this method checks whether the new calendar older than the current calendar or in the future
     *
     * @param calendar the potential new one
     * @throws Exception thrown if calendar is older than the current one or in the future
     */
    public void updateWithExc4(Calendar calendar) throws Exception {
        if (calendar.before(lastUpdate)) {
            throw new BadUpdateTimeException(calendar, true);
        }

        if (calendar.after(Calendar.getInstance())) {
            throw new BadUpdateTimeException(calendar, false);
        }

        lastUpdate = calendar;
    }

    /**
     * this method checks whether the new calendar older than the current calendar or in the future
     *
     * @param calendar the potential new one
     * @throws Exception thrown if calendar is older than the current one or in the future
     */
    public void updateWithExc5(Calendar calendar) throws Exception {
        if (calendar.before(lastUpdate)) {
            throw new Exception("Update time is earlier than the last update: "
                + calendar.get(Calendar.DAY_OF_MONTH)
                + "." + (calendar.get(Calendar.MONTH) + 1)
                + "." + calendar.get(Calendar.YEAR)
                + " / " + calendar.get(Calendar.HOUR_OF_DAY)
                + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND)
                + ":" + calendar.get(Calendar.MILLISECOND)
                + "!");
        }

        if (calendar.after(Calendar.getInstance())) {
            throw new Exception("Update time is in the future: "
                + calendar.get(Calendar.DAY_OF_MONTH)
                + "." + (calendar.get(Calendar.MONTH) + 1)
                + "." + calendar.get(Calendar.YEAR)
                + " / " + calendar.get(Calendar.HOUR_OF_DAY)
                + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND)
                + ":" + calendar.get(Calendar.MILLISECOND)
                + "!");
        }

        lastUpdate = calendar;
    }
}
