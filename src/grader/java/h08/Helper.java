package h08;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Calendar;

@TestForSubmission("h08")
class Helper {

    public static final int tolerance = 80;


    static Calendar createFutureCal() {
        Calendar futureCal = Calendar.getInstance();

        /*
        if (futureCal.get(Calendar.MILLISECOND) < 990) {
            futureCal.set(Calendar.MILLISECOND, futureCal.get(Calendar.MILLISECOND) + 9);
        } else if (futureCal.get(Calendar.SECOND) < 58) {
            futureCal.set(Calendar.SECOND, futureCal.get(Calendar.SECOND) + 1);
        } else if (futureCal.get(Calendar.MINUTE) < 58) {
            futureCal.set(Calendar.MINUTE, futureCal.get(Calendar.MINUTE) + 1);
        } else {
            futureCal.set(Calendar.YEAR, futureCal.get(Calendar.YEAR) + 1);
        }

         */
        futureCal.set(Calendar.YEAR, futureCal.get(Calendar.YEAR) + 1);
        return futureCal;
    }

    static Calendar createPastCal() {
        Calendar pastCal = Calendar.getInstance();
        /*
        if (pastCal.get(Calendar.MILLISECOND) > 10) {
            pastCal.set(Calendar.MILLISECOND, pastCal.get(Calendar.MILLISECOND) - 9);
        } else if (pastCal.get(Calendar.SECOND) > 2) {
            pastCal.set(Calendar.SECOND, pastCal.get(Calendar.SECOND) - 1);
        } else if (pastCal.get(Calendar.MINUTE) > 2) {
            pastCal.set(Calendar.MINUTE, pastCal.get(Calendar.MINUTE) - 1);
        } else {
            pastCal.set(Calendar.YEAR, pastCal.get(Calendar.YEAR) - 1);
        }

         */
        pastCal.set(Calendar.YEAR, pastCal.get(Calendar.YEAR) - 1);

        return pastCal;
    }

    static String[] createCorrectMessage(Calendar calendar, boolean time) {
        String message[] = new String[tolerance];
        for (int i = 0; i<tolerance; i++) {
            int millisec = calendar.get(Calendar.MILLISECOND) + i;
            message[i] = (time ? "Update time is earlier than the last update: " : "Update time is in the future: ")
                + calendar.get(Calendar.DAY_OF_MONTH)
                + "." + (calendar.get(Calendar.MONTH) + 1)
                + "." + calendar.get(Calendar.YEAR)
                + " / " + calendar.get(Calendar.HOUR_OF_DAY)
                + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND)
                + ":" + millisec
                + "!";
        }
        return message;
    }

    static String createMessageOfBadUpdateException(Calendar calendar, boolean time) {
        return (new BadUpdateTimeException(calendar, time)).getMessage();
    }
}
