package h08;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Calendar;

@TestForSubmission("h08")
class Helper {
    static Calendar createFutureCal() {
        Calendar futureCal = Calendar.getInstance();

        if (futureCal.get(Calendar.MILLISECOND) < 990) {
            futureCal.set(Calendar.MILLISECOND, futureCal.get(Calendar.MILLISECOND) + 9);
        } else if (futureCal.get(Calendar.SECOND) < 58) {
            futureCal.set(Calendar.SECOND, futureCal.get(Calendar.SECOND) + 1);
        } else if (futureCal.get(Calendar.MINUTE) < 58) {
            futureCal.set(Calendar.MINUTE, futureCal.get(Calendar.MINUTE) + 1);
        } else {
            futureCal.set(Calendar.YEAR, futureCal.get(Calendar.YEAR) + 1);
        }

        return futureCal;
    }

    static String createCorrectMessage(Calendar calendar, boolean time) {
        return (time ? "Update time is earlier than the last update: " : "Update time is in the future: ")
            + calendar.get(Calendar.DAY_OF_MONTH)
            + "." + (calendar.get(Calendar.MONTH) + 1)
            + "." + calendar.get(Calendar.YEAR)
            + " / " + calendar.get(Calendar.HOUR_OF_DAY)
            + ":" + calendar.get(Calendar.MINUTE)
            + ":" + calendar.get(Calendar.SECOND)
            + ":" + calendar.get(Calendar.MILLISECOND)
            + "!";
    }

    static String createMessageOfBadUpdateException(Calendar calendar, boolean time) {
        return (new BadUpdateTimeException(calendar, time)).getMessage();
    }
}
