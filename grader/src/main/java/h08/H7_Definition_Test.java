package h08;

import h08.roommanagement.NoCertificateException;
import h08.roommanagement.Room;
import h08.roommanagement.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Calendar;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

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
