package h08;

import java.util.Calendar;

/**
 * class for h08.
 */
public class TestTimeStampExceptions {

    //H5

    /**
     * tests the timestamp function updateWithExc_n_ (param) and prints out information about thrown exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @param n         which method updateWithExc_n_ should be used? if n<1 || n>5 nothing happens
     */
    public void testCatch1(TimeStamp timestamp, Calendar calendar, int n) {
        try {
            test(timestamp, calendar, n);
        } catch (UpdateTimeBeforeLastUpdateException e) {
            System.out.println(n + " : UpdateTimeBeforeLastUpdateException : " + e.getClass().getSimpleName() + " "
                + e.getMessage());
        } catch (UpdateTimeInTheFutureException e) {
            System.out.println(n + " : UpdateTimeInTheFutureException : " + e.getClass().getSimpleName() + " " + e.getMessage());
        } catch (Exception ignored) {
        }
    }

    /**
     * tests the timestamp function updateWithExc_n_ (param) and prints out information about thrown exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @param n         which method updateWithExc_n_ should be used? if n<1 || n>5 nothing happens
     */
    public void testCatch2(TimeStamp timestamp, Calendar calendar, int n) {
        try {
            test(timestamp, calendar, n);
        } catch (UpdateTimeBeforeLastUpdateException
            | UpdateTimeInTheFutureException e) {
            System.out.println(n + " : UpdateTimeBeforeLastUpdateException oder UpdateTimeInTheFutureException : "
                + e.getClass().getSimpleName() + " " + e.getMessage());
        } catch (Exception ignored) {
        }
    }

    /**
     * tests the timestamp function updateWithExc_n_ (param) and prints out information about thrown exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @param n         which method updateWithExc_n_ should be used? if n<1 || n>5 nothing happens
     */
    public void testCatch3(TimeStamp timestamp, Calendar calendar, int n) {
        try {
            test(timestamp, calendar, n);
        } catch (UpdateTimeBeforeLastUpdateException e) {
            System.out.println(n + " : UpdateTimeBeforeLastUpdateException : "
                + e.getClass().getSimpleName() + " " + e.getMessage());
        } catch (BadUpdateTimeException e) {
            System.out.println(n + " : BadUpdateTimeException : " + e.getClass().getSimpleName() + " " + e.getMessage());
        } catch (Exception ignored) {
        }
    }

    /**
     * tests the timestamp function updateWithExc_n_ (param) and prints out information about thrown exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @param n         which method updateWithExc_n_ should be used? if n<1 || n>5 nothing happens
     */
    public void testCatch4(TimeStamp timestamp, Calendar calendar, int n) {
        try {
            test(timestamp, calendar, n);
        } catch (BadUpdateTimeException e) {
            System.out.println(n + " : BadUpdateTimeException : " + e.getClass().getSimpleName() + " " + e.getMessage());
        } catch (Exception ignored) {
        }
    }

    /**
     * tests the timestamp function updateWithExc_n_ (param) and prints out information about thrown exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @param n         which method updateWithExc_n_ should be used? if n<1 || n>5 nothing happens
     */
    public void testCatch5(TimeStamp timestamp, Calendar calendar, int n) {
        try {
            test(timestamp, calendar, n);
        } catch (Exception e) {
            System.out.println(n + " : Exception : " + e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }

    /**
     * calls the correct method updateWithExcn in dependency to n and throws its Exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @param n         which method updateWithExc_n_ should be used? if n<1 || n>5 nothing happens
     * @throws Exception which is thrown by the method chosen with param n
     */
    private void test(TimeStamp timestamp, Calendar calendar, int n) throws Exception {
        switch (n) {
            case 1:
                timestamp.updateWithExc1(calendar);
                break;
            case 2:
                timestamp.updateWithExc2(calendar);
                break;
            case 3:
                timestamp.updateWithExc3(calendar);
                break;
            case 4:
                timestamp.updateWithExc4(calendar);
                break;
            case 5:
                timestamp.updateWithExc5(calendar);
                break;
            default:
                break;
        }
    }

    //H6

    /**
     * calls updateWithExc1 with given parameters and passes Exception.
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     * @throws BadUpdateTimeException thrown by timestamp.updateWithException1(calendar)
     */
    public void testPass(TimeStamp timestamp, Calendar calendar) throws BadUpdateTimeException {
        timestamp.updateWithExc1(calendar);
    }

    /**
     * calls testPass with own parameters and catches potentially thrown exception. Prints out information about exception
     *
     * @param timestamp object which is necessary to test the methods of TimeStamp, has current calendar
     * @param calendar  potential new calendar
     */
    public void testCatchPassed(TimeStamp timestamp, Calendar calendar) {
        try {
            testPass(timestamp, calendar);
        } catch (BadUpdateTimeException e) {
            System.out.println("BadUpdateTimeException : " + e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }
}
