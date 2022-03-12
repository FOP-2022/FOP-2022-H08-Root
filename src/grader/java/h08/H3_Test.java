package h08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H3.
 */
@TestForSubmission("h08")
public class H3_Test {

    @Test
    public void testConstructorExistenceBadUpdateTimeException() {
        int exceptions = 0;
        try {
            BadUpdateTimeException.class.getDeclaredConstructor(Calendar.class, boolean.class);
        } catch (NoSuchMethodException e) {
            exceptions++;
        }
        try {
            BadUpdateTimeException.class.getDeclaredConstructor(boolean.class, Calendar.class);
        } catch (NoSuchMethodException e) {
            exceptions++;
        }
        assertTrue(exceptions < 2, "correct constructor of BadUpdateTimeException does not exist");
    }

    @Test
    public void testConstructorContentBadUpdateTimeException() {
        Calendar[] calendars = createManyRandomCalendars();

        for (Calendar c : calendars) {
            boolean equals= false;
            String[] results = Helper.createCorrectMessage(c, true);
            String message = new BadUpdateTimeException(c, true).getMessage();

            for (int i = 0; i< Helper.tolerance; i++) {
                if (results[i].equals(message)) {
                    equals = true;
                }
            }
            assertTrue(equals, "constructor of BadUpdateTimeException returns wrong message at least in true case: should be <" + results[0] + "> but was <" + message + ">");

            equals= false;
            results = Helper.createCorrectMessage(c, false);
            message = new BadUpdateTimeException(c, false).getMessage();

            for (int i = 0; i< Helper.tolerance; i++) {
                if (results[i].equals(message)) {
                    equals = true;
                }
            }
            assertTrue(equals, "constructor of BadUpdateTimeException returns wrong message in false case: should be <" + results[0] + "> but was <" + message + ">");
        }
    }

    @Test
    public void testConstructorExistenceUpdateTimeBeforeLastUpdateException() {
        try {
            UpdateTimeBeforeLastUpdateException.class.getDeclaredConstructor(Calendar.class);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            fail("constructor of UpdateTimeBeforeLastUpdateException wrong");
        }
    }

    @ExtendWith(JagrExecutionCondition.class)
    @Test
    public void testConstructorContentUpdateTimeBeforeLastUpdateException() {
        //wie testet man, dass auch wirklich der Konstruktor der Superklasse aufgerufen
        // wird und nicht der String neu erzeugt wird?
        Calendar[] calendars = createManyRandomCalendars();

        ExceptionConstructorVerifier.reset();

        for (Calendar c : calendars) {
            assertEquals(Helper.createMessageOfBadUpdateException(c, true),
                new UpdateTimeBeforeLastUpdateException(c).getMessage(),
                "constructor of UpdateTimeBeforeLastUpdateException returns wrong message");
        }

        assertEquals(ExceptionConstructorVerifier.correctDescriptor, ExceptionConstructorVerifier.descriptor,
            "BadUpdateTimeException has incorrect constructor");
        if (ExceptionConstructorVerifier.isVar2 == null) {
            fail("Kontaktieren sie Ihren Ansprechpartner");
        } else {
            // is set by call of UpdateTimeInTheFutureException constructor
            assertTrue(ExceptionConstructorVerifier.isVar2);
            // hier muss Test auf wahren Konstruktoraufruf von BadUpdateTimeException hin
        }
    }

    @Test
    public void testConstructorExistenceUpdateTimeInTheFutureException() {
        try {
            UpdateTimeInTheFutureException.class.getDeclaredConstructor(Calendar.class);
        } catch (NoSuchMethodException e) {
            fail("constructor of UpdateTimeInTheFutureException wrong", e);
        }
    }

    @ExtendWith(JagrExecutionCondition.class)
    @Test
    public void testConstructorContentUpdateTimeInTheFutureException() {
        Calendar[] calendars = createManyRandomCalendars();

        ExceptionConstructorVerifier.reset();

        for (Calendar c : calendars) {
            assertEquals(Helper.createMessageOfBadUpdateException(c, false), new UpdateTimeInTheFutureException(c).getMessage(),
                "constructor of UpdateTimeInTheFutureException returns wrong message");
        }

        assertEquals(ExceptionConstructorVerifier.correctDescriptor, ExceptionConstructorVerifier.descriptor,
            "BadUpdateTimeException has incorrect constructor");
        if (ExceptionConstructorVerifier.isVar2 == null) {
            fail("Kontaktieren sie Ihren Ansprechpartner");
        } else {
            // is set by call of UpdateTimeInTheFutureException constructor
            assertFalse(ExceptionConstructorVerifier.isVar2);
            // hier muss Test auf wahren Konstruktoraufruf von BadUpdateTimeException hin
        }
    }

    Calendar[] createManyRandomCalendars() {
        int nrOfTests = 100;

        Calendar[] calendars = new Calendar[nrOfTests];
        calendars[0] = Calendar.getInstance();
        for (int i = 1; i < nrOfTests - 1; i++) {
            calendars[i] = Calendar.getInstance();
            calendars[i].set(Calendar.YEAR, (int) (Math.random() * 3000) + 1);
            calendars[i].set(Calendar.MONTH, (int) (Math.random() * 12));
            calendars[i].set(Calendar.DAY_OF_MONTH, (int) (Math.random() * 31) + 1);
            calendars[i].set(Calendar.HOUR_OF_DAY, (int) (Math.random() * 24));
            calendars[i].set(Calendar.MINUTE, (int) (Math.random() * 60));
            calendars[i].set(Calendar.SECOND, (int) (Math.random() * 60));
            calendars[i].set(Calendar.MILLISECOND, (int) (Math.random() * 1000));
        }
        calendars[nrOfTests - 1] = Calendar.getInstance();

        return calendars;
    }
}
