package h08.tutor;


import java.util.Calendar;

public class UpdateTimeBeforeLastUpdateException extends BadUpdateTimeException {

    public UpdateTimeBeforeLastUpdateException(Calendar calendar) {
        super(calendar, false);
        // TODO Auto-generated constructor stub
    }

}
