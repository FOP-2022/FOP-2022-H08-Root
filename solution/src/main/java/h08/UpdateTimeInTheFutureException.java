package h08;

import java.util.Calendar;

public class UpdateTimeInTheFutureException extends BadUpdateTimeException {

  public UpdateTimeInTheFutureException(Calendar calendar) {
    super(calendar, false);
    // TODO Auto-generated constructor stub
  }
}
