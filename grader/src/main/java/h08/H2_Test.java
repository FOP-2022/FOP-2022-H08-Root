package h08;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Calendar;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H2.
 */
@TestForSubmission("h08")
public class H2_Test {

    @Test
    public void testDefinitionTimeStampUpdateWithParameter() {
        Method[] methods = TimeStamp.class.getDeclaredMethods();
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("update")), "method update does not exist");

        boolean hasUpdateWithParameter = false;
        for (Method m : methods) {
            if (m.getName().equals("update")) {
                assertTrue(isPublic(m.getModifiers()), "method update is not public");
                assertFalse(isStatic(m.getModifiers()), "method update is static");
                assertEquals(void.class, m.getReturnType(), "method update is not void");

                Parameter[] parameter = m.getParameters();
                if (parameter.length == 1) {
                    hasUpdateWithParameter = true;
                    assertSame(parameter[0].getType(), Calendar.class, "method update has wrong parameter type");
                }
            }
        }
        assertTrue(hasUpdateWithParameter, "method update has wrong parameter count");
    }

    @Test
    public void testContentTimeStampUpdateWithParameterCorrectCase() throws IllegalArgumentException, IllegalAccessException {
        // content tests
        TimeStamp instance = new TimeStamp();

        Field[] fields = TimeStamp.class.getDeclaredFields();
        Field f = null;
        for (Field field : fields) {
            if (field.getName().equals("lastUpdate")) {
                field.setAccessible(true);
                f = field;
            }
        }

        Calendar variableValueBefore = (Calendar) f.get(instance);

        //should work

        Calendar toAdd = Calendar.getInstance();

        try {
            instance.update(toAdd);
        } catch (AssertionError e) {
            fail("AssertionError thrown although input correct");
        }

        assertSame(toAdd, f.get(instance), "method update does not overwrite lastUpdate correctly");
    }

    @Test
    public void testContentTimeStampUpdateWithParameterTooLateCase() throws IllegalArgumentException, IllegalAccessException {
        TimeStamp instance = new TimeStamp();

        Field[] fields = TimeStamp.class.getDeclaredFields();
        Field f = null;
        for (Field field : fields) {
            if (field.getName() == "lastUpdate") {
                field.setAccessible(true);
                f = field;
            }
        }

        Calendar variableValueBefore = (Calendar) f.get(instance);
        boolean errorThrown = false;

        //time too late
        variableValueBefore = (Calendar) f.get(instance);
        Calendar futureCal = Helper.createFutureCal();
        try {
            instance.update(futureCal);
        } catch (AssertionError e) {
            errorThrown = true;
        }

        assertTrue(errorThrown, "no AssertionError thrown although Calender in the future");
        assertSame(variableValueBefore, f.get(instance), "lastUpdate was changed although Calendar in the future");
    }

    @Test
    public void testContentTimeStampUpdateWithParameterTooEarlyCase() throws IllegalArgumentException, IllegalAccessException {



        TimeStamp instance = new TimeStamp();
        Calendar before = Calendar.getInstance();
        before.set(2020, 5, 15);

        Field[] fields = TimeStamp.class.getDeclaredFields();
        Field f = null;
        for (Field field : fields) {
            if (field.getName() == "lastUpdate") {
                field.setAccessible(true);
                f = field;
            }
        }
        //time too early

        Calendar variableValueBefore = (Calendar) f.get(instance);
        boolean errorThrown = false;
        try {
            instance.update(before);
        } catch (AssertionError e) {
            errorThrown = true;
        }
        assertTrue(errorThrown, "no AssertionError thrown although Calender too old");

        assertSame(variableValueBefore, f.get(instance), "lastUpdate was changed although Calendar too old");
    }
}
