package h08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Calendar;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h08")
public class H4_Test {

    @ParameterizedTest(name = "updateWithExc{index}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testExistenceUpdateWithExc(int nr) {
        String methodName = "updateWithExc" + nr;

        Method[] methods = TimeStamp.class.getDeclaredMethods();
        boolean hasMethod = false;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                assertTrue(isPublic(m.getModifiers()), "method " + methodName + " is not public");
                assertFalse(isStatic(m.getModifiers()), "method " + methodName + " is static");
                assertEquals(void.class, m.getReturnType(), "method " + methodName + " is not void");

                Parameter[] parameter = m.getParameters();
                assertSame(parameter[0].getType(), Calendar.class);
                assertEquals(1, parameter.length);
                hasMethod = true;
            }
        }
        assertTrue(hasMethod, "method " + methodName + " does not exist");
    }

    @Test
    public void testContentUpdateWithExc123() throws IllegalArgumentException, IllegalAccessException {
        for (int i = 1; i <= 3; i++) {
            testContentUpdateWithExc(i);
        }
    }

    @Test
    public void testContentUpdateWithExc4() throws IllegalArgumentException, IllegalAccessException {
        testContentUpdateWithExc(4);
    }

    @Test
    public void testContentUpdateWithExc5() throws IllegalArgumentException, IllegalAccessException {
        testContentUpdateWithExc(5);
    }

    //@ParameterizedTest(name = "updateWithExc{index}")
    //@ValueSource(ints = {1, 2, 3, 4, 5})
    void testContentUpdateWithExc(int nr) throws IllegalArgumentException, IllegalAccessException {

        Method[] methods = TimeStamp.class.getDeclaredMethods();

        Method method = null;
        for (Method m : methods) {
            if (m.getName().equals("updateWithExc" + nr)) {
                method = m;
            }
        }

        assertNotNull(method); // sollte nicht vorkommen, da schon in testExistenceUpdateWithExc geprüft

        // content tests
        Calendar before = Calendar.getInstance();
        Helper.sleep();
        TimeStamp instance = new TimeStamp();
        Helper.sleep();
        Calendar after = Calendar.getInstance();

        Field[] fields = TimeStamp.class.getDeclaredFields();
        Field f = null;
        for (Field field : fields) {
            if (field.getName().equals("lastUpdate")) {
                field.setAccessible(true);
                f = field;
            }
        }

        //situation where it should fail

        //time too early

        Calendar variableValueBefore = (Calendar) f.get(instance);
        boolean errorThrown = false;
        try {
            method.invoke(instance, before);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            if (nr < 4)
                assertEquals(e.getCause().getClass(), UpdateTimeBeforeLastUpdateException.class, "time of Calendar too early: wrong Exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
            if (nr == 4) {
                assertEquals(e.getCause().getClass(), BadUpdateTimeException.class, "time of Calendar too early: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
                assertTrue(e.getCause().getMessage().contains("Update time is earlier than the last update: "), "time of Calendar too early: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getMessage() + " (probably wrong exception)");
            }
            if (nr == 5) {
                assertEquals(e.getCause().getClass(), Exception.class, "time of Calendar too early: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
                assertTrue(e.getCause().getMessage().contains("Update time is earlier than the last update: "), "time of Calendar too early: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getMessage() + " (probably wrong exception)");
            }

            errorThrown = true;
        }
        assertTrue(errorThrown, "time of Calendar too early: method updateWithExc" + nr + " does not throw any exception");

        assertSame(variableValueBefore, f.get(instance), "time of Calendar too early: method updateWithExc" + nr + " changes value of lastUpdate");

        //time too late

        Calendar futureCal = Helper.createFutureCal();

        errorThrown = false;
        variableValueBefore = (Calendar) f.get(instance);

        try {
            method.invoke(instance, futureCal);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            if (nr < 4)
                assertEquals(e.getCause().getClass(), UpdateTimeInTheFutureException.class, "time of Calendar in the future: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
            if (nr == 4) {
                assertEquals(e.getCause().getClass(), BadUpdateTimeException.class, "time of Calendar in the future: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
                assertTrue(e.getCause().getMessage().contains("Update time is in the future: "), "time of Calendar in the future: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getMessage() + " (probably wrong exception)");
            }
            if (nr == 5) {
                assertEquals(e.getCause().getClass(), Exception.class, "time of Calendar in the future: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
                assertTrue(e.getCause().getMessage().contains("Update time is in the future: "), "time of Calendar in the future: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getClass() + " (probably wrong exception)");
            }

            errorThrown = true;
        }

        assertTrue(errorThrown, "time of Calendar in the future: method updateWithExc" + nr + " does not throw any exception");
        assertSame(variableValueBefore, f.get(instance), "time of Calendar in the future: method updateWithExc" + nr + " changes value of lastUpdate");

        Helper.sleep();

        //should work

        Calendar toAdd = Calendar.getInstance();
        Helper.sleep();

        errorThrown = false;
        variableValueBefore = (Calendar) f.get(instance);

        try {
            method.invoke(instance, toAdd);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            fail("correct Calendar, but exception thrown in method updateWithExc " + nr + ": " + e.getCause().getClass());
        }

        assertSame(toAdd, f.get(instance), "correct Calendar, but lastUpdate not updated");
    }
}
