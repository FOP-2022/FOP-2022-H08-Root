package h08;

import java.util.Calendar;

public class UpdateTimeBeforeLastUpdateException extends BadUpdateTimeException {

	public UpdateTimeBeforeLastUpdateException(Calendar calendar) {
		super(calendar, true);
		// TODO Auto-generated constructor stub
	}

}
