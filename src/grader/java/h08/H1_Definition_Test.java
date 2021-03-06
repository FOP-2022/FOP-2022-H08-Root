package h08;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Field;
import java.util.Calendar;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H1 definitions.
 */
@TestForSubmission("h08")
public class H1_Definition_Test {

    @Test
    public void classExistence() throws ClassNotFoundException {
        try {
            Class.forName("h08.TimeStamp");
        } catch (ClassNotFoundException e) {
            fail("Class TimeStamp does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.TimeStamp").getModifiers()), "Class TimeStamp is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.TimeStamp").getModifiers()), "Class TimeStamp is not public");
    }

    @Test
    public void testConstructorExistence() {
        try {
            TimeStamp.class.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fail("Constructor does not exist", e);
        }
    }

    @Test
    public void testDefinitionAndAttribute() {
        Field[] fields = TimeStamp.class.getDeclaredFields();
        boolean containsLastUpdate = false;
        for (Field field : fields) {
            if (field.getName().equals("lastUpdate")) {
                containsLastUpdate = true;
                assertTrue(isPrivate(field.getModifiers()), "lastUpdate is not private");
                assertEquals(Calendar.class, field.getType(), "lastUpdate is not Calendar");
                break;
            }
        }

        assertTrue(containsLastUpdate, "Attribute lastUpdate is missing");
    }
}
