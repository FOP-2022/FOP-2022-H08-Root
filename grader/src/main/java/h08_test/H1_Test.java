package h08_test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.reflect.Modifier.*;

import h08.TimeStamp;

class H1_Test {

	
	
	
	
	@BeforeEach
	void testConstuctorContent() throws IllegalAccessException{
		
		Calendar before = Calendar.getInstance();
		TimeStamp instance = new TimeStamp();
		Calendar after = Calendar.getInstance();
		Field[] fields = TimeStamp.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName() == "lastUpdate") {
				field.setAccessible(true);
				assertFalse(before.after(field.get(instance)));
				assertFalse(after.before(field.get(instance)));
			}
		}
	}
	
	
	
	@BeforeEach
	void testTimeStampUpdateWithoutParameter () throws IllegalArgumentException, IllegalAccessException {
		Method[] methods = TimeStamp.class.getDeclaredMethods();
		assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("update")));
		
		boolean hasUpdateWithoutParameters = false;
		for (Method m : methods) {
			if (m.getName() == "update") {
				assertTrue(isPublic(m.getModifiers()));
				assertFalse(isStatic(m.getModifiers()));
				assertTrue(void.class.equals(m.getReturnType()));
				
				Parameter[] parameter = m.getParameters();
				if (parameter.length == 0)
					hasUpdateWithoutParameters = true;	
			}
		}
		assertTrue(hasUpdateWithoutParameters);
		
		
		// content tests
		TimeStamp instance = new TimeStamp();
		Calendar before = Calendar.getInstance();
		instance.update();
		Calendar after = Calendar.getInstance();
		Field[] fields = TimeStamp.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName() == "lastUpdate") {
				field.setAccessible(true);
				assertFalse(before.after(field.get(instance)));
				assertFalse(after.before(field.get(instance)));
				assertTrue(GregorianCalendar.class.equals(field.get(instance).getClass()));
				
			}
		}
		
	}
	
	@BeforeEach
	void testGetTimeStamp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = TimeStamp.class.getDeclaredMethods();
		assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getTimeStamp")));
		
		boolean hasGetTimeStampWithCorrectParameters = false;
		for (Method m : methods) {
			if (m.getName() == "getTimeStamp") {
				assertTrue(isPublic(m.getModifiers()));
				assertFalse(isStatic(m.getModifiers()));
				assertTrue(Calendar.class.equals(m.getReturnType()));
				
				Parameter[] parameter = m.getParameters();
				if (parameter.length == 0)
					hasGetTimeStampWithCorrectParameters = true;	
			}
		}
		assertTrue(hasGetTimeStampWithCorrectParameters);
		
		
		
		// content tests
		TimeStamp instance = new TimeStamp();
		Calendar returnValue = instance.getTimeStamp();
		
		for(Method method : methods) {
			if (method.getName() == "getTimeStamp") {
				assertTrue(returnValue == (method.invoke(instance)));
			}
		}
				
	}
	
	@Test 
	void content() {}

}
