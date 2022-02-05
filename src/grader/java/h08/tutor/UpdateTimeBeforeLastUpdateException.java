package h08.tutor;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Calendar;

@TestForSubmission("h08")
public class UpdateTimeBeforeLastUpdateException extends h08.UpdateTimeBeforeLastUpdateException {

    private final Calendar calendar;

    public UpdateTimeBeforeLastUpdateException(Calendar calendar) {
        super(calendar);
        this.calendar = calendar;
        // TODO Auto-generated constructor stub
    }

    public String getMessage() {
        return new BadUpdateTimeException(calendar, true).getMessage();
    }
}
