package h08;

import java.util.Calendar;

/**
 * class for h08.
 */
public class UpdateTimeBeforeLastUpdateException extends BadUpdateTimeException {

    public UpdateTimeBeforeLastUpdateException(Calendar calendar) {
        super(calendar, true);
    }
}
