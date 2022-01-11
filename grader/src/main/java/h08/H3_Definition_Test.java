package h08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h08")
public class H3_Definition_Test {

    @BeforeEach
    public void checkClassBadUpdateTimeException() throws ClassNotFoundException {
        try {
            Class.forName("h08.BadUpdateTimeException");
        } catch (ClassNotFoundException e) {
            fail("class BadUpdateTimeException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.BadUpdateTimeException").getModifiers()), "class BadUpdateTimeException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.BadUpdateTimeException").getModifiers()), "class BadUpdateTimeException is not public");
        //superclass correct
        assertEquals(Class.forName("h08.BadUpdateTimeException").getSuperclass(), Exception.class, "class BadUpdateTimeException does not extend class Exception");
    }

    @Test
    @BeforeEach
    public void checkClassUpdateTimeBeforeLastUpdateException() throws ClassNotFoundException {
        try {
            Class.forName("h08.UpdateTimeBeforeLastUpdateException");
        } catch (ClassNotFoundException e) {
            fail("class UpdateTimeBeforeLastUpdateException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()), "class UpdateTimeBeforeLastUpdateException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()), "class UpdateTimeBeforeLastUpdateException is not public");
        //superclass correct
        assertEquals(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getSuperclass(), BadUpdateTimeException.class, "class UpdateTimeBeforeLastUpdateException does not extend class BadUpdateTimeException");
    }

    @Test
    @BeforeEach
    void checkClassUpdateTimeInTheFutureException() throws ClassNotFoundException {
        try {
            Class.forName("h08.UpdateTimeInTheFutureException");
        } catch (ClassNotFoundException e) {
            fail("class UpdateTimeInTheFutureException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()), "class UpdateTimeInTheFutureException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()), "class UpdateTimeInTheFutureException is not public");
        //superclass correct
        assertEquals(Class.forName("h08.UpdateTimeInTheFutureException").getSuperclass(), BadUpdateTimeException.class, "class UpdateTimeInTheFutureException does not extend class BadUpdateTimeException");
    }

    @Test
    public void checkClasses() {
    }
}
