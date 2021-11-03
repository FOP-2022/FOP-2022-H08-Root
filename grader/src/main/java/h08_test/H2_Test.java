package h08_test;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import h08.TimeStamp;

class H2_Test {

	@Test
	void testDefinitionTimeStampUpdateWithParameter () {
		Method[] methods = TimeStamp.class.getDeclaredMethods();
		assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("update")));
		
		boolean hasUpdateWithParameter = false;
		for (Method m : methods) {
			if (m.getName() == "update") {
				assertTrue(isPublic(m.getModifiers()));
				assertFalse(isStatic(m.getModifiers()));
				assertTrue(void.class.equals(m.getReturnType()));
				
				Parameter[] parameter = m.getParameters();
				if (parameter.length == 1) {
					hasUpdateWithParameter = true;
					assertTrue(parameter[0].getType() == Calendar.class);
				}
						
			}
		}
		assertTrue(hasUpdateWithParameter);
		
	}
	
	
	@Test
	void testContentTimeStampUpdateWithParameterCorrectCase () throws IllegalArgumentException, IllegalAccessException {
		// content tests
				Calendar before = Calendar.getInstance();
				sleep();
				TimeStamp instance = new TimeStamp();
				sleep();
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
				boolean errorThrown = false;
				
				int reps = 10;
					//should work
				for (int i = 0; i< reps ; i++) {
					Calendar toAdd = Calendar.getInstance();
					sleep();
					
					errorThrown = false;
					variableValueBefore = (Calendar) f.get(instance);
					
					try {
						instance.update(toAdd);
					} catch (AssertionError e) {
						errorThrown = true;
					}
					
					assertFalse(errorThrown);
					assertTrue(toAdd == f.get(instance));
				}
	}
	
	@Test
	void testContentTimeStampUpdateWithParameterTooLateCase() throws IllegalArgumentException, IllegalAccessException {
		Calendar before = Calendar.getInstance();
		sleep();
		TimeStamp instance = new TimeStamp();
		sleep();
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
		boolean errorThrown = false;
		
		int reps = 10;
		//time too late
		for ( int i = 0; i< reps; i++) {
			Calendar futureCal = Calendar.getInstance();
			
			if (futureCal.get(Calendar.MILLISECOND) < 998) {
				futureCal.set(Calendar.MILLISECOND, futureCal.get(Calendar.MILLISECOND) + 2);
			} else if (futureCal.get(Calendar.SECOND) <58) {
				futureCal.set(Calendar.SECOND, futureCal.get(Calendar.SECOND) + 2);
			} else if (futureCal.get(Calendar.MINUTE) <59) {
				futureCal.set(Calendar.MINUTE, futureCal.get(Calendar.MINUTE) + 1);
			} else {
				futureCal.set(Calendar.YEAR, futureCal.get(Calendar.YEAR) + 1);
			}
			
			errorThrown = false;
			variableValueBefore = (Calendar) f.get(instance);
			
			try {
				instance.update(futureCal);
			} catch (AssertionError e) {
				errorThrown = true;
			}
			
			assertTrue(errorThrown);
			assertTrue(variableValueBefore == f.get(instance));
			
			sleep();	
		}
	}
	
	
	@Test
	void testContentTimeStampUpdateWithParameterTooEarlyCase() throws IllegalArgumentException, IllegalAccessException {
		
	Calendar before = Calendar.getInstance();
	sleep();
	TimeStamp instance = new TimeStamp();
	sleep();
	Calendar after = Calendar.getInstance();
	
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
	assertTrue(errorThrown);
	
	assertTrue(variableValueBefore == f.get(instance));
	
	
	}
	
	void sleep() {
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
