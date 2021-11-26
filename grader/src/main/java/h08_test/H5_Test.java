package h08_test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import h08.BadUpdateTimeException;
import h08.TestTimeStampExceptions;
import h08.TimeStamp;
import h08.UpdateTimeBeforeLastUpdateException;
import h08.UpdateTimeInTheFutureException;


class H5_Test {
	private final String[] staticExceptionBeforeLastUpdateStatic = {"UpdateTimeBeforeLastUpdateException", "UpdateTimeBeforeLastUpdateException oder UpdateTimeInTheFutureException", null, "BadUpdateTimeException", "Exception"};
	private final String[] staticExceptionUpdateTimeInTheFutureStatic = {"UpdateTimeInTheFutureException", "UpdateTimeBeforeLastUpdateException oder UpdateTimeInTheFutureException", "BadUpdateTimeException", "BadUpdateTimeException", "Exception"};
	private final String[] staticExceptionBeforeLastUpdateDynamic = {"UpdateTimeBeforeLastUpdateException", "UpdateTimeBeforeLastUpdateException", "UpdateTimeBeforeLastUpdateException", "BadUpdateTimeException", "Exception"};
	private final String[] staticExceptionUpdateTimeInTheFutureDynamic = {"UpdateTimeInTheFutureException", "UpdateTimeInTheFutureException", "UpdateTimeInTheFutureException", "BadUpdateTimeException", "Exception"};
	private final int[] nrOutputPrinted = {3, 3, 4, 4, 5};
	
	private ByteArrayOutputStream outContent;
	
	@Test
	void testContentTestCatch1115_3135() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for (int i = 11; i<=15; i++) {
			testContentTestCatch(i);
		}
		for (int i = 31; i<=35; i++) {
			testContentTestCatch(i);
		}
	}
	
	@Test
	void testContentTestCatch2125() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for (int i = 21; i<=25; i++) {
			testContentTestCatch(i);
		}
	}
	
	@Test
	void testContentTestCatch4145_5155() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for (int i = 41; i<=45; i++) {
			testContentTestCatch(i);
		}
		for (int i = 51; i<=55; i++) {
			testContentTestCatch(i);
		}
	}
	
	
	//@ParameterizedTest(name = "testCatchn ->{0}<- updateWithExcn")
	//@ValueSource(ints = {11, 12, 13, 14, 15, 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55})
	void testContentTestCatch(int n) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		int updateWithExcNr = n % 10;
		int testCatchNr = (int) (n/10);
		
		TestTimeStampExceptions exceptions = new TestTimeStampExceptions();
		
		//content
		
		
		// wie überprüft man, dass switch-case angewendet wird?
		
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
				Helper.sleep();
				TimeStamp instance = new TimeStamp();
				Helper.sleep();
				Calendar after = Calendar.getInstance();
				
				Field[] fields = TimeStamp.class.getDeclaredFields();
				Field f = null;
				for (Field field : fields) {
					if (field.getName() == "lastUpdate") {
						field.setAccessible(true);
						f = field;
					}
				}
				
				
				
					//situation where it should fail
				
						//time too early
				 
				Calendar variableValueBefore = (Calendar) f.get(instance);
				
				methodCatch.invoke(exceptions, instance, before, updateWithExcNr);
				//exceptions.testCatch1(instance, before, updateWithExcNr);
				if (updateWithExcNr <= nrOutputPrinted[testCatchNr - 1]) {
					String compareString;
					if (staticExceptionBeforeLastUpdateStatic[testCatchNr-1] == null) {
						compareString = outputBegin + (updateWithExcNr == 4 ? "BadUpdateTimeException" : "UpdateTimeBeforeLastUpdateException") + " : " + staticExceptionBeforeLastUpdateDynamic[updateWithExcNr-1] + " " + Helper.createCorrectMessage(before, true)  + "\n";
					} else {
						compareString = outputBegin + staticExceptionBeforeLastUpdateStatic[testCatchNr-1] + " : " + staticExceptionBeforeLastUpdateDynamic[updateWithExcNr-1] + " " + Helper.createCorrectMessage(before, true)  + "\n";
					}
					
					assertEquals(outContent.toString(), compareString, "time of Calendar is too early: output message of method testCatch" + testCatchNr + " is wrong");
				}else
					assertEquals(outContent.toString(), "", "time of Calendar is too early: output message of method testCatch" + testCatchNr + " is not empty");
				
				renewOutContent();
				
				
				assertTrue(variableValueBefore == f.get(instance), "time of Calendar is too early: lastUpdate of method testCatch" + testCatchNr + " was changed");
				
				
						//time too late
				
				Calendar futureCal = Helper.createFutureCal();
				
				
				variableValueBefore = (Calendar) f.get(instance);
				methodCatch.invoke(exceptions, instance, futureCal, updateWithExcNr);
				//exceptions.testCatch1(instance, futureCal, updateWithExcNr);
				if (updateWithExcNr <= nrOutputPrinted[testCatchNr - 1])
					assertEquals(outContent.toString(), outputBegin + staticExceptionUpdateTimeInTheFutureStatic[testCatchNr-1] + " : " + staticExceptionUpdateTimeInTheFutureDynamic[updateWithExcNr-1] + " " + Helper.createCorrectMessage(futureCal, false)  + "\n", "time of Calendar in the future: output message of method testCatch" + testCatchNr + " is wrong");
				else
					assertEquals(outContent.toString(), "", "time of Calendar in the future: output message of method testCatch" + testCatchNr + " is not empty");
				renewOutContent();
				
				
				assertTrue(variableValueBefore == f.get(instance), "time of Calendar in the future: lastUpdate of method testCatch" + testCatchNr + " was changed");
					
					
				
				
				
					
		
		
		System.setOut(System.out);
		
	}
	
	
	@ParameterizedTest(name = "testCatchn ->{0}<- updateWithExcn")
	@ValueSource(ints = {11, 12, 13, 14, 15, 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55})
	void testContentTestCatchShouldWork (int n) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		TestTimeStampExceptions exceptions = new TestTimeStampExceptions();
		
		int updateWithExcNr = n % 10;
		
		
		Method[] methodsCatch = TestTimeStampExceptions.class.getDeclaredMethods();
		
		Method methodCatch = null;
		int testCatchNr = (int) (n /10);
		for (Method m : methodsCatch) {
			
			if (m.getName().equals("testCatch" + testCatchNr)) {
				methodCatch = m;
			}
		}
		
		renewOutContent();
		
		
		
		Calendar before = Calendar.getInstance();
		Helper.sleep();
		TimeStamp instance = new TimeStamp();
		Helper.sleep();
		Calendar after = Calendar.getInstance();
		
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
		Helper.sleep();
		
		variableValueBefore = (Calendar) f.get(instance);
		
		methodCatch.invoke(exceptions, instance, toAdd, updateWithExcNr);
		//exceptions.testCatch1(instance, toAdd, n);
		assertEquals(outContent.toString(), "", "correct Calendar: method testCatch" + testCatchNr + " prints something, probably exception is thrown");
		
		
		assertTrue(toAdd == f.get(instance), "correct Calendar: method testCatch" + testCatchNr + " does not change lastUpdate");
		
		
		System.setOut(System.out);
	}
	
	private void renewOutContent() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

}
