package h08.tutor;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Calendar;
@TestForSubmission("h08")
public class UpdateTimeInTheFutureException extends h08.UpdateTimeInTheFutureException {
    private Calendar calendar;
    public UpdateTimeInTheFutureException(Calendar calendar) {

        super(calendar);
        this.calendar = calendar;
    }

    public String getMessage() {
        return new BadUpdateTimeException(calendar, false).getMessage();
    }
}
