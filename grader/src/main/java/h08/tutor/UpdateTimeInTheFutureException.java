package h08.tutor;

import java.util.Calendar;

public class UpdateTimeInTheFutureException extends BadUpdateTimeException {

    public UpdateTimeInTheFutureException(Calendar calendar) {
        super(calendar, false);
    }
}
