package h08;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H7 definitions.
 */
@TestForSubmission("h08")
public class H7_Definition_Test {

    @Test
    void testClassExistenceInsufficentNumberOfSeatsException() throws ClassNotFoundException {
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
        assertEquals(Class.forName("h08.roommanagement.InsufficientNumberOfSeatsException").getSuperclass(),
            RuntimeException.class, "class InsufficientNumberOfSeatsException does "
                + "not extend class RuntimeException");
    }

    @Test
    void testClassExistenceNoCertificateException() throws ClassNotFoundException {
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
        assertEquals(Class.forName("h08.roommanagement.NoCertificateException").getSuperclass(), Exception.class,
            "class NoCertificateException does not extend class Exception");
    }
}
