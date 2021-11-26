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
				assertFalse(before.after(field.get(instance)), "new Calendar not correctly generated: too early date");
				assertFalse(after.before(field.get(instance)), "new Calendar not correctly generated: too late date");
			}
		}
	}
	
	
	
	@BeforeEach
	void testTimeStampUpdateWithoutParameter () throws IllegalArgumentException, IllegalAccessException {
		Method[] methods = TimeStamp.class.getDeclaredMethods();
		assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("update")), "there is no method update");
		
		boolean hasUpdateWithoutParameters = false;
		for (Method m : methods) {
			if (m.getName() == "update") {
				assertTrue(isPublic(m.getModifiers()), "method update is not public");
				assertFalse(isStatic(m.getModifiers()), "method update is static");
				assertTrue(void.class.equals(m.getReturnType()), "method update is not void");
				
				Parameter[] parameter = m.getParameters();
				if (parameter.length == 0)
					hasUpdateWithoutParameters = true;	
			}
		}
		assertTrue(hasUpdateWithoutParameters, "there is no method update without any parameter");
		
		
		// content tests
		TimeStamp instance = new TimeStamp();
		Helper.sleep();
		Calendar before = Calendar.getInstance();
		instance.update();
		Calendar after = Calendar.getInstance();
		Helper.sleep();
		Field[] fields = TimeStamp.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName() == "lastUpdate") {
				field.setAccessible(true);
				assertFalse(before.after(field.get(instance)), "update process returns too early Calender / is not done");
				assertFalse(after.before(field.get(instance)), "update process returns too late Calendar");
				assertTrue(GregorianCalendar.class.equals(field.get(instance).getClass()), "update does not create a GregorianCalendar");
				
			}
		}
		
	}
	
	@BeforeEach
	void testGetTimeStamp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = TimeStamp.class.getDeclaredMethods();
		assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getTimeStamp")), "method getTimeStamp does not exist");
		
		boolean hasGetTimeStampWithCorrectParameters = false;
		Parameter[] parameter = null;
		for (Method m : methods) {
			if (m.getName() == "getTimeStamp") {
				assertTrue(isPublic(m.getModifiers()), "method getTimeStamp is not public");
				assertFalse(isStatic(m.getModifiers()), "method getTimeStamp is static");
				assertTrue(Calendar.class.equals(m.getReturnType()), "method getTimeStamp does not return Calendar");
				
				parameter = m.getParameters();
				if (parameter.length == 0)
					hasGetTimeStampWithCorrectParameters = true;	
			}
		}
		assertTrue(hasGetTimeStampWithCorrectParameters, "method getTimeStamp has incorrect parameters");
		
		
		
		// content tests
		Calendar returnValue1 = Calendar.getInstance();
		TimeStamp instance = new TimeStamp();
		Calendar returnValue2 = Calendar.getInstance();
		
		for(Method method : methods) {
			if (method.getName() == "getTimeStamp") {
				assertTrue(returnValue1.equals(method.invoke(instance)) || returnValue2.equals(method.invoke(instance)), "getTimeStamp returns incorrect Calendar");
			}
		}
				
	}
	
	@Test 
	void content() {}

}
