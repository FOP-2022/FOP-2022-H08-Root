package h08_test;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import h08.BadUpdateTimeException;
import h08.TestTimeStampExceptions;
import h08.TimeStamp;
import h08.UpdateTimeBeforeLastUpdateException;
import h08.UpdateTimeInTheFutureException;






class H6_Test {

	
	private ByteArrayOutputStream outContent;
	
	
	
	@Test
	void testExistenceTestPass() {
		String methodName = "testPass";
		
		Method[] methods = TestTimeStampExceptions.class.getDeclaredMethods();
		boolean hasMethod = false;
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				assertTrue(isPublic(m.getModifiers()));
				assertFalse(isStatic(m.getModifiers()));
				assertTrue(void.class.equals(m.getReturnType()));
				
				Parameter[] parameter = m.getParameters();
				boolean containsParam = false;
				Object[] returnClasses = {Calendar.class, TimeStamp.class};
				for (Parameter p : parameter) {
					for (Object retClass : returnClasses) {
						if (retClass.equals(p.getType()))
							containsParam = true;
					}
					assertTrue(containsParam);
					containsParam = false;
				}
				
				assertTrue(parameter.length == 2);
				hasMethod = true;
			}
			
			
		}
		assertTrue(hasMethod);
		
		
		TestTimeStampExceptions tte = new TestTimeStampExceptions();
		TimeStamp instance = new TimeStamp();
		Calendar cal = Calendar.getInstance();
		try {
			tte.testPass(instance, cal);
		} catch (BadUpdateTimeException e) {
			// TODO Auto-generated catch block
			
		}
		
		
		
	}
	
	@Test
	void testExistenceTestCatchPassed() {
		String methodName = "testCatchPassed";
		
		Method[] methods = TestTimeStampExceptions.class.getDeclaredMethods();
		boolean hasMethod = false;
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				assertTrue(isPublic(m.getModifiers()));
				assertFalse(isStatic(m.getModifiers()));
				assertTrue(void.class.equals(m.getReturnType()));
				
				Parameter[] parameter = m.getParameters();
				boolean containsParam = false;
				Object[] returnClasses = {Calendar.class, TimeStamp.class};
				for (Parameter p : parameter) {
					for (Object retClass : returnClasses) {
						if (retClass.equals(p.getType()))
							containsParam = true;
					}
					assertTrue(containsParam);
					containsParam = false;
				}
				
				assertTrue(parameter.length == 2);
				hasMethod = true;
			}
			
			
		}
		assertTrue(hasMethod);
		
	}
	
	
	@Test
	void testTestPassContent() throws  IllegalAccessException {
		
		
		// content tests
		Calendar before = Calendar.getInstance();
		Helper.sleep();
		TimeStamp instance = new TimeStamp();
		Helper.sleep();
		
		
		Field[] fields = TimeStamp.class.getDeclaredFields();
		Field f = null;
		for (Field field : fields) {
			if (field.getName() == "lastUpdate") {
				field.setAccessible(true);
				f = field;
			}
		}
		
		TestTimeStampExceptions tte = new TestTimeStampExceptions();
		
		
		
			//situation where it should fail
		
				//time too early
		 
		Calendar variableValueBefore = (Calendar) f.get(instance);
		boolean errorThrown = false;
		try {
			tte.testPass(instance, before);
		} catch (UpdateTimeBeforeLastUpdateException e) {
			// TODO Auto-generated catch block	
			errorThrown = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("wrong exception: " + e.getMessage());
		}
		assertTrue(errorThrown);
		
		assertTrue(variableValueBefore == f.get(instance));
		
		
				//time too late
		
			
		Calendar futureCal=Helper.createFutureCal();
		
		errorThrown = false;
		variableValueBefore = (Calendar) f.get(instance);
		
		try {
			tte.testPass(instance, futureCal);
		} catch (UpdateTimeInTheFutureException e) {
			// TODO Auto-generated catch block
				
			errorThrown = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("wrong exception: " + e.getMessage());
		}	
		
		
		assertTrue(errorThrown);
		assertTrue(variableValueBefore == f.get(instance));
		
		Helper.sleep();	
		
		
		
			//should work
		
		Calendar toAdd = Calendar.getInstance();
		Helper.sleep();
		
		errorThrown = false;
		variableValueBefore = (Calendar) f.get(instance);
		
		
		try {
			tte.testPass(instance, toAdd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception thrown although should work");
		} 
		
		
		assertTrue(toAdd == f.get(instance));
		
	
	}
	
	
	
	@Test
	void testTestCatchPassedContent() throws IllegalArgumentException, IllegalAccessException {
		TestTimeStampExceptions exceptions = new TestTimeStampExceptions();
		
		//content
		
		
		// wie überprüft man, dass switch-case angewendet wird?		
		
		renewOutContent();
		
		
		
		// content tests
				Calendar before = Calendar.getInstance();
				Helper.sleep();
				TimeStamp instance = new TimeStamp();
				Helper.sleep();
				
				
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
				
				exceptions.testCatchPassed(instance, before);
				//exceptions.testCatch1(instance, before, updateWithExcNr);
				
				String compareString = "BadUpdateTimeException" + " : " + "UpdateTimeBeforeLastUpdateException" + " " + Helper.createCorrectMessage(before, true) + "\n";
					
					
				assertEquals(outContent.toString(), compareString);
				
				
				renewOutContent();
				
				
				assertTrue(variableValueBefore == f.get(instance));
				
				
						//time too late
				
				Calendar futureCal = Helper.createFutureCal();
				
				
				variableValueBefore = (Calendar) f.get(instance);
				exceptions.testCatchPassed(instance, futureCal);
				//exceptions.testCatch1(instance, futureCal, updateWithExcNr);
				compareString = "BadUpdateTimeException" + " : " + "UpdateTimeInTheFutureException" + " " + Helper.createCorrectMessage(futureCal, false) + "\n";
				
				assertEquals(outContent.toString(), compareString);
				
				renewOutContent();
				
				
				assertTrue(variableValueBefore == f.get(instance));
					
		
		
		System.setOut(System.out);
		
	}
	
	
	private void renewOutContent() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

}
