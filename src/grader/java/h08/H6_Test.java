package h08;

import h08.tutor.TimeStamp2;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Calendar;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H6.
 */
@TestForSubmission("h08")
public class H6_Test {

    private ByteArrayOutputStream outContent;

    @Test
    public void testExistenceTestPass() {
        String methodName = "testPass";

        Method[] methods = TestTimeStampExceptions.class.getDeclaredMethods();
        boolean hasMethod = false;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                assertTrue(isPublic(m.getModifiers()), "method testPass is not public");
                assertFalse(isStatic(m.getModifiers()), "method testPass is static");
                assertEquals(void.class, m.getReturnType(), "method testPass is not void");

                Parameter[] parameter = m.getParameters();
                boolean containsParam = false;
                Object[] returnClasses = {Calendar.class, TimeStamp.class};
                for (Parameter p : parameter) {
                    for (Object retClass : returnClasses) {
                        if (retClass.equals(p.getType())) {
                            containsParam = true;
                        }
                    }
                    assertTrue(containsParam, "method testPass does not contain at least one parameter");
                    containsParam = false;
                }

                assertEquals(2, parameter.length, "method testPass has too many parameters");
                hasMethod = true;
            }
        }
        assertTrue(hasMethod, "method testPass does not exist");

        TestTimeStampExceptions tte = new TestTimeStampExceptions();
        TimeStamp instance = new TimeStamp();
        Calendar cal = Calendar.getInstance();
        try {
            tte.testPass(instance, cal);
        } catch (BadUpdateTimeException ignored) {
        }
    }

    @Test
    public void testExistenceTestCatchPassed() {
        String methodName = "testCatchPassed";

        Method[] methods = TestTimeStampExceptions.class.getDeclaredMethods();
        boolean hasMethod = false;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                assertTrue(isPublic(m.getModifiers()), "method testCatchPass is not public");
                assertFalse(isStatic(m.getModifiers()), "method testCatchPass is static");
                assertEquals(void.class, m.getReturnType(), "method testCatchPass is not void");

                Parameter[] parameter = m.getParameters();
                boolean containsParam = false;
                Object[] returnClasses = {Calendar.class, TimeStamp.class};
                for (Parameter p : parameter) {
                    for (Object retClass : returnClasses) {
                        if (retClass.equals(p.getType())) {
                            containsParam = true;
                        }
                    }
                    assertTrue(containsParam, "method testCatchPass does not contain at least one parameter");
                    containsParam = false;
                }

                assertEquals(2, parameter.length, "method testCatchPass has too many parameters");
                hasMethod = true;
            }
        }
        assertTrue(hasMethod, "method testCatchPass does not exist");
    }

    @Test
    public void testTestPassContent() throws IllegalAccessException {

        // content tests
        TimeStamp instance = new TimeStamp();
        Calendar before = Helper.createPastCal();


        TestTimeStampExceptions tte = new TestTimeStampExceptions();

        //situation where it should fail

        //time too early

        boolean errorThrown = false;
        try {
            tte.testPass(instance, before);
        } catch (UpdateTimeBeforeLastUpdateException e) {
            errorThrown = true;
        } catch (Exception e) {
            fail("time of Calendar is too early: method testPass throws wrong exception", e);
        }
        assertTrue(errorThrown, "time of Calendar is too early: method testPass does not throw any exception");

        //time too late

        Calendar futureCal = Helper.createFutureCal();

        errorThrown = false;

        try {
            tte.testPass(instance, futureCal);
        } catch (UpdateTimeInTheFutureException ignored) {
            errorThrown = true;
        } catch (Exception e) {
            fail("time of Calendar in the future: method testPass throws wrong exception", e);
        }

        assertTrue(errorThrown, "time of Calendar in the future: method testPass does not throw any exception");

        //should work

        Calendar toAdd = Calendar.getInstance();


        try {
            tte.testPass(instance, toAdd);
        } catch (Exception e) {
            fail("correct Calendar: method testPass throws exception", e);
        }
    }

    @Test
    public void testTestCatchPassedContent() throws IllegalArgumentException, IllegalAccessException {

        //content



        renewOutContent();

        // content tests


        //situation where it should fail

        //time too early

        TimeStamp2 instance = new TimeStamp2();
        Calendar before = Helper.createPastCal();

        TestTimeStampExceptions exceptions = new TestTimeStampExceptions();

        exceptions.testCatchPassed(instance, before);

        assertNotEquals("", outContent.toString(),
            "time of Calendar is too early: method testCatchPass writes no message");

        renewOutContent();

        //time too late

        Calendar futureCal = Helper.createFutureCal();

        exceptions.testCatchPassed(instance, futureCal);

        assertNotEquals("", outContent.toString(),
            "time of Calendar in the future: method testPass does not throw any Exception");

        renewOutContent();

        System.setOut(System.out);
    }

    private void renewOutContent() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
}
