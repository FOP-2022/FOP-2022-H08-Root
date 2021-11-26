package h08_test;


import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import h08.BadUpdateTimeException;
import h08.TimeStamp;
import h08.UpdateTimeBeforeLastUpdateException;
import h08.UpdateTimeInTheFutureException;



class H4_Test {
	
	@ParameterizedTest(name = "updateWithExc{index}")
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void testExistenceUpdateWithExc(int nr) {
		String methodName = "updateWithExc" + nr;
		
		Method[] methods = TimeStamp.class.getDeclaredMethods();
		boolean hasMethod = false;
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				assertTrue(isPublic(m.getModifiers()), "method " + methodName + " is not public");
				assertFalse(isStatic(m.getModifiers()), "method " + methodName + " is static");
				assertTrue(void.class.equals(m.getReturnType()), "method " + methodName + " is not void");
				
				Parameter[] parameter = m.getParameters();
				assertTrue(parameter[0].getType() == Calendar.class);
				assertTrue(parameter.length == 1);
				hasMethod = true;
			}
		}
		assertTrue(hasMethod, "method " + methodName + " does not exist");
		
	}
	
	
	
	@Test
	void testContentUpdateWithExc123() throws IllegalArgumentException, IllegalAccessException {
		for (int i = 1; i<=3; i++) {
			testContentUpdateWithExc(i);
		}
	}
	
	@Test
	void testContentUpdateWithExc4() throws IllegalArgumentException, IllegalAccessException {
		testContentUpdateWithExc(4);
	}
	
	@Test
	void testContentUpdateWithExc5() throws IllegalArgumentException, IllegalAccessException {
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
		
		assertFalse(method == null); // sollte nicht vorkommen, da schon in testExistenceUpdateWithExc geprüft
		
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
		boolean errorThrown = false;
		try {
			method.invoke(instance, before);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			if (nr < 4)
				assertTrue(e.getCause().getClass().equals(UpdateTimeBeforeLastUpdateException.class), "time of Calendar too early: wrong Exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass() );
			if (nr == 4) {
				assertTrue(e.getCause().getClass().equals(BadUpdateTimeException.class), "time of Calendar too early: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass() );
				assertTrue(e.getCause().getMessage().contains("Update time is earlier than the last update: "), "time of Calendar too early: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getMessage() + " (probably wrong exception)" );
			}
			if (nr == 5) {
				assertTrue(e.getCause().getClass().equals(Exception.class), "time of Calendar too early: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass() );
				assertTrue(e.getCause().getMessage().contains("Update time is earlier than the last update: "), "time of Calendar too early: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getMessage() + " (probably wrong exception)");
			}
				
			errorThrown = true;
		}
		assertTrue(errorThrown, "time of Calendar too early: method updateWithExc" + nr + " does not throw any exception");
		
		assertTrue(variableValueBefore == f.get(instance), "time of Calendar too early: method updateWithExc" + nr + " changes value of lastUpdate");
		
		
				//time too late
		
			
		Calendar futureCal=Helper.createFutureCal();
		
		errorThrown = false;
		variableValueBefore = (Calendar) f.get(instance);
		
		try {
			method.invoke(instance, futureCal);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			if (nr < 4)
				assertTrue(e.getCause().getClass().equals(UpdateTimeInTheFutureException.class), "time of Calendar in the future: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
			if (nr == 4) {
				assertTrue(e.getCause().getClass().equals(BadUpdateTimeException.class), "time of Calendar in the future: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
				assertTrue(e.getCause().getMessage().contains("Update time is in the future: "), "time of Calendar in the future: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getMessage() + " (probably wrong exception)");
			}
			if (nr == 5) {
				assertTrue(e.getCause().getClass().equals(Exception.class), "time of Calendar in the future: wrong exception type thrown in method updateWithExc" + nr + ": " + e.getCause().getClass());
				assertTrue(e.getCause().getMessage().contains("Update time is in the future: "), "time of Calendar in the future: wrong message in exception of method updateWithExc" + nr + ": " + e.getCause().getClass() + " (probably wrong exception)");
			}
				
			errorThrown = true;
		}	
		
		
		assertTrue(errorThrown, "time of Calendar in the future: method updateWithExc" + nr + " does not throw any exception");
		assertTrue(variableValueBefore == f.get(instance), "time of Calendar in the future: method updateWithExc" + nr + " changes value of lastUpdate");
		
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
		
		assertTrue(toAdd == f.get(instance), "correct Calendar, but lastUpdate not updated");
		
	}
	
	

}
