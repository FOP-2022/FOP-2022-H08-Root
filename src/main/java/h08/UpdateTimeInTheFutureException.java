package h08;

import java.util.Calendar;

/**
 * class for h08.
 */
public class UpdateTimeInTheFutureException extends BadUpdateTimeException {

    public UpdateTimeInTheFutureException(Calendar calendar) {
        super(calendar, false);
    }
}
