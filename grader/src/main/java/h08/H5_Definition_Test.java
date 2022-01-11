package h08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Calendar;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H5 definitions.
 */
@TestForSubmission("h08")
public class H5_Definition_Test {

    @Test
    @BeforeEach
    public void classExistence() throws ClassNotFoundException {
        try {
            Class.forName("h08.TestTimeStampExceptions");
        } catch (ClassNotFoundException e) {
            fail("Class TestTimeStampExceptions does not exist");
        }

        // not abstract
        assertFalse(isAbstract(Class.forName("h08.TimeStamp").getModifiers()), "Class TestTimeStampExceptions is abstract");
        //is public
        assertTrue(isPublic(Class.forName("h08.TimeStamp").getModifiers()), "Class TestTimeStampExceptions is abstract");
    }


    @ParameterizedTest(name = "testCatch{index}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testExistenceTestCatch(int nr) {
        String methodName = "testCatch" + nr;

        Method[] methods = TestTimeStampExceptions.class.getDeclaredMethods();
        boolean hasMethod = false;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                assertTrue(isPublic(m.getModifiers()), "method " + methodName + " is not public");
                assertFalse(isStatic(m.getModifiers()), "method " + methodName + " is static");
                assertEquals(void.class, m.getReturnType(), "method " + methodName + " is not void");

                Parameter[] parameter = m.getParameters();
                boolean containsParam = false;
                Object[] returnClasses = {Calendar.class, TimeStamp.class, int.class};
                for (Parameter p : parameter) {
                    for (Object retClass : returnClasses) {
                        if (retClass.equals(p.getType())) {
                            containsParam = true;
                        }
                    }
                    assertTrue(containsParam, "method " + methodName + " does not have all parameter");
                    containsParam = false;
                }

                assertEquals(3, parameter.length, "method " + methodName + " has too many parameters");
                hasMethod = true;
            }
        }
        assertTrue(hasMethod, "method " + methodName + " does not exist");
    }
}
