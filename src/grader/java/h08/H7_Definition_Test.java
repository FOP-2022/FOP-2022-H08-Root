package h08;

import h08.roommanagement.InsufficientNumberOfSeatsException;
import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H7 definitions.
 */
@TestForSubmission("h08")
public class H7_Definition_Test {

    @Test
    public void testClassExistenceInsufficientNumberOfSeatsException() throws ClassNotFoundException {
        try {
            Class.forName("h08.roommanagement.InsufficientNumberOfSeatsException");
        } catch (ClassNotFoundException e) {
            fail("class InsufficientNumberOfSeatsException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.roommanagement.InsufficientNumberOfSeatsException").getModifiers()),
            "class InsufficientNumberOfSeatsException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.roommanagement.InsufficientNumberOfSeatsException").getModifiers()),
            "class InsufficientNumberOfSeatsException is not public");
        //superclass correct
        assertEquals(RuntimeException.class,
            Class.forName("h08.roommanagement.InsufficientNumberOfSeatsException").getSuperclass(),
            "class InsufficientNumberOfSeatsException does "
                + "not extend class RuntimeException");
    }

    @Test
    public void testClassExistenceNoCertificateException() throws ClassNotFoundException {
        try {
            Class.forName("h08.roommanagement.NoCertificateException");
        } catch (ClassNotFoundException e) {
            fail("class NoCertificateException does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.roommanagement.NoCertificateException").getModifiers()),
            "class NoCertificateException is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.roommanagement.NoCertificateException").getModifiers()),
            "class NoCertificateException is not public");
        //superclass correct
        assertEquals(Exception.class, Class.forName("h08.roommanagement.NoCertificateException").getSuperclass(),
            "class NoCertificateException does not extend class Exception");
    }

    @Test
    public void testConstructorExistenceNoCertificateException() {
        Constructor<?>[] allconstructors = null;
        try {
            NoCertificateException.class.getDeclaredConstructor(Student[].class);
            allconstructors = InsufficientNumberOfSeatsException.class.getConstructors();
        } catch (NoSuchMethodException e) {
            fail("constructor of UpdateTimeBeforeLastUpdateException wrong", e);
        }
        assertEquals(1, allconstructors.length, "there is more than one constructor in InsufficientNumberOfSeatsException");
    }

    @Test
    public void testConstructorExistenceInsufficientNumberOfSeatsException() {
        Constructor<?>[] allconstructors = null;
        try {
            InsufficientNumberOfSeatsException.class.getDeclaredConstructor(Room.class, int.class);
            allconstructors = InsufficientNumberOfSeatsException.class.getConstructors();
        } catch (NoSuchMethodException e) {
            fail("constructor of UpdateTimeBeforeLastUpdateException wrong", e);
        }
        assertEquals(1, allconstructors.length, "there is more than one constructor in InsufficientNumberOfSeatsException");
    }
}
