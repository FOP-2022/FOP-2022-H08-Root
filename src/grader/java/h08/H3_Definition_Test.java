package h08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H3 definitions.
 */
@TestForSubmission("h08")
public class H3_Definition_Test {
    @Test
    public void checkClassBadUpdateTimeException() throws ClassNotFoundException {
        try {
            Class.forName("h08.BadUpdateTimeException");
        } catch (ClassNotFoundException e) {
            fail("class BadUpdateTimeException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.BadUpdateTimeException").getModifiers()),
            "class BadUpdateTimeException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.BadUpdateTimeException").getModifiers()),
            "class BadUpdateTimeException is not public");
        //superclass correct
        assertEquals(Exception.class, Class.forName("h08.BadUpdateTimeException").getSuperclass(),
            "class BadUpdateTimeException does not extend class Exception");
    }

    @Test
    public void checkClassUpdateTimeBeforeLastUpdateException() throws ClassNotFoundException {
        try {
            Class.forName("h08.UpdateTimeBeforeLastUpdateException");
        } catch (ClassNotFoundException e) {
            fail("class UpdateTimeBeforeLastUpdateException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()),
            "class UpdateTimeBeforeLastUpdateException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()),
            "class UpdateTimeBeforeLastUpdateException is not public");
        //superclass correct
        assertEquals(BadUpdateTimeException.class, Class.forName("h08.UpdateTimeBeforeLastUpdateException").getSuperclass(),
            "class UpdateTimeBeforeLastUpdateException does not extend class BadUpdateTimeException");
    }

    @Test
    public void checkClassUpdateTimeInTheFutureException() throws ClassNotFoundException {
        try {
            Class.forName("h08.UpdateTimeInTheFutureException");
        } catch (ClassNotFoundException e) {
            fail("class UpdateTimeInTheFutureException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()),
            "class UpdateTimeInTheFutureException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()),
            "class UpdateTimeInTheFutureException is not public");
        //superclass correct
        assertEquals(BadUpdateTimeException.class, Class.forName("h08.UpdateTimeInTheFutureException").getSuperclass(),
            "class UpdateTimeInTheFutureException does not extend class BadUpdateTimeException");
    }
}
