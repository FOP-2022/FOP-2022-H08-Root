package h08;

import h08.reflection.ClassTester;
import h08.reflection.MethodTester;
import javassist.bytecode.analysis.ControlFlow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtTry;
import spoon.reflect.visitor.filter.TypeFilter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The JUnit tests for H5.
 */
@TestForSubmission("h08")
public class H5_Test {
    private final String[] staticExceptionBeforeLastUpdateStatic = {
        "UpdateTimeBeforeLastUpdateException",
        "UpdateTimeBeforeLastUpdateException oder UpdateTimeInTheFutureException",
        null,
        "BadUpdateTimeException",
        "Exception",
    };
    private final String[] staticExceptionUpdateTimeInTheFutureStatic = {
        "UpdateTimeInTheFutureException",
        "UpdateTimeBeforeLastUpdateException oder UpdateTimeInTheFutureException",
        "BadUpdateTimeException",
        "BadUpdateTimeException",
        "Exception"};
    private final String[] staticExceptionBeforeLastUpdateDynamic = {
        "UpdateTimeBeforeLastUpdateException",
        "UpdateTimeBeforeLastUpdateException",
        "UpdateTimeBeforeLastUpdateException",
        "BadUpdateTimeException",
        "Exception",
    };
    private final String[] staticExceptionUpdateTimeInTheFutureDynamic = {
        "UpdateTimeInTheFutureException",
        "UpdateTimeInTheFutureException",
        "UpdateTimeInTheFutureException",
        "BadUpdateTimeException",
        "Exception",
    };
    private final int[] nrOutputPrinted = {3, 3, 4, 4, 5};

    private ByteArrayOutputStream outContent;

    @Test
    public void testContentTestCatch1115_3135()
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        for (int i = 11; i <= 15; i++) {
            testContentTestCatch(i);
        }
        for (int i = 31; i <= 35; i++) {
            testContentTestCatch(i);
        }
    }

    @Test
    public void testContentTestCatch2125()
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        for (int i = 21; i <= 25; i++) {
            testContentTestCatch(i);
        }

        ClassTester<TestTimeStampExceptions> classtester = new ClassTester<TestTimeStampExceptions>(TestTimeStampExceptions.class).assureClassResolved();
        MethodTester methodTester = new MethodTester(classtester, "testCatch2").assureMethodResolved();
        assertTrue(methodTester.assertCtMethodExists().getElements(new TypeFilter<>(CtTry.class)).get(0).getCatchers().size() == 2);

        methodTester.assertConstructsUsed(List.of(CtCatch.class));

        //Kontrolle, dass Catch Klausel nur einen Catch-Block enthält

    }

    @Test
    public void testContentTestCatch4145_5155()
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        for (int i = 41; i <= 45; i++) {
            testContentTestCatch(i);
        }
        for (int i = 51; i <= 55; i++) {
            testContentTestCatch(i);
        }
    }

    //@ParameterizedTest(name = "testCatchn ->{0}<- updateWithExcn")
    //@ValueSource(ints = {11, 12, 13, 14, 15, 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55})
    void testContentTestCatch(int n)
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        int updateWithExcNr = n % 10;
        int testCatchNr = n / 10;



        //content

        Method[] methodsCatch = TestTimeStampExceptions.class.getDeclaredMethods();

        Method methodCatch = null;
        for (Method m : methodsCatch) {
            if (m.getName().equals("testCatch" + testCatchNr)) {
                methodCatch = m;
            }
        }

        String outputBegin = updateWithExcNr + " : ";
        renewOutContent();

        // content tests

        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        before.set(before.get(Calendar.YEAR) - 2, 5, 15);
        after.set(after.get(Calendar.YEAR) + 2, 5, 15);
        TimeStamp instance = new TimeStamp();

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
        final Calendar variableValueBefore1 = (Calendar) f.get(instance);
        TestTimeStampExceptions exceptions = new TestTimeStampExceptions();
        methodCatch.invoke(exceptions, instance, before, updateWithExcNr);
        //exceptions.testCatch1(instance, before, updateWithExcNr);
        if (updateWithExcNr <= nrOutputPrinted[testCatchNr - 1]) {
            String compareString;
            if (staticExceptionBeforeLastUpdateStatic[testCatchNr - 1] == null) {
                compareString = String.format("%s%s : %s %s\n",
                    outputBegin,
                    updateWithExcNr == 4 ? "BadUpdateTimeException" : "UpdateTimeBeforeLastUpdateException",
                    staticExceptionBeforeLastUpdateDynamic[updateWithExcNr - 1],
                    Helper.createCorrectMessage(before, true));
            } else {
                compareString = String.format("%s%s : %s %s\n",
                    outputBegin,
                    staticExceptionBeforeLastUpdateStatic[testCatchNr - 1],
                    staticExceptionBeforeLastUpdateDynamic[updateWithExcNr - 1],
                    Helper.createCorrectMessage(before, true));
            }

            assertEquals(outContent.toString(), compareString,
                "time of Calendar is too early: output message of method testCatch" + testCatchNr + " is wrong");
        } else {
            assertEquals(outContent.toString(), "",
                "time of Calendar is too early: output message of method testCatch" + testCatchNr + " is not empty");
        }

        renewOutContent();

        assertSame(variableValueBefore1, f.get(instance),
            "time of Calendar is too early: lastUpdate of method testCatch" + testCatchNr + " was changed");

        //time too late

        Calendar futureCal = Helper.createFutureCal();

        final Calendar variableValueBefore2 = (Calendar) f.get(instance);
        methodCatch.invoke(exceptions, instance, futureCal, updateWithExcNr);
        //exceptions.testCatch1(instance, futureCal, updateWithExcNr);
        if (updateWithExcNr <= nrOutputPrinted[testCatchNr - 1]) {
            assertEquals(outContent.toString(),
                String.format("%s%s : %s %s\n",
                    outputBegin,
                    staticExceptionUpdateTimeInTheFutureStatic[testCatchNr - 1],
                    staticExceptionUpdateTimeInTheFutureDynamic[updateWithExcNr - 1],
                    Helper.createCorrectMessage(futureCal, false)),
                "time of Calendar in the future: output message of method testCatch" + testCatchNr + " is wrong");
        } else {
            assertEquals(outContent.toString(), "",
                "time of Calendar in the future: output message of method testCatch" + testCatchNr + " is not empty");
        }
        renewOutContent();

        assertSame(variableValueBefore2, f.get(instance),
            "time of Calendar in the future: lastUpdate of method testCatch" + testCatchNr + " was changed");

        System.setOut(System.out);
    }


    @ParameterizedTest(name = "testCatchn ->{0}<- updateWithExcn")
    @ValueSource(ints = {11, 12, 13, 14, 15, 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55})
    public void testContentTestCatchShouldWork(int n) throws IllegalArgumentException, IllegalAccessException,
        InvocationTargetException {


        final int updateWithExcNr = n % 10;

        Method[] methodsCatch = TestTimeStampExceptions.class.getDeclaredMethods();

        Method methodCatch = null;
        int testCatchNr = n / 10;
        for (Method m : methodsCatch) {

            if (m.getName().equals("testCatch" + testCatchNr)) {
                methodCatch = m;
            }
        }

        renewOutContent();


        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        before.set(before.get(Calendar.YEAR) - 2, 5, 15);
        after.set(after.get(Calendar.YEAR) + 2, 5, 15);
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

        //should work

        Calendar toAdd = Calendar.getInstance();

        variableValueBefore = (Calendar) f.get(instance);
        TestTimeStampExceptions exceptions = new TestTimeStampExceptions();
        methodCatch.invoke(exceptions, instance, toAdd, updateWithExcNr);
        //exceptions.testCatch1(instance, toAdd, n);
        assertEquals(outContent.toString(), "",
            "correct Calendar: method testCatch" + testCatchNr + " prints something, probably exception is thrown");

        assertSame(toAdd, f.get(instance),
            "correct Calendar: method testCatch" + testCatchNr + " does not change lastUpdate");

        System.setOut(System.out);
    }

    @Test
    public void testSwitchCase() {

    }

    private void renewOutContent() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
}
