package h08_test;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import h08.TimeStamp;

class H1_Definition_Test {

	@BeforeEach
	void classExistence() throws ClassNotFoundException {
		try {
			Class.forName("h08.TimeStamp");
		} catch (ClassNotFoundException e) {
			fail("Class TimeStamp does not exist");
		}
		
		
		
		// not abstract
		assertFalse(isAbstract(Class.forName("h08.TimeStamp").getModifiers()), "Class TimeStamp is abstract");
		//is public
		assertTrue(isPublic(Class.forName("h08.TimeStamp").getModifiers()), "Class TimeStamp is not public");
		
		
	}
	
	@BeforeEach
	void testConstructorExistence() {
		try {
			Constructor constructor = TimeStamp.class.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			fail("Constructor does not exist");
		}
		
	}
	
	
	
	@Test
	void testDefinitionAndAttribute() {
		Field[] fields = TimeStamp.class.getDeclaredFields();
		boolean containsLastUpdate = false;
		for (Field field : fields) {
			if (field.getName() == "lastUpdate") {
				containsLastUpdate = true;
				assertTrue(isPrivate(field.getModifiers()));
				assertTrue(Calendar.class.equals(field.getType()));
				break;
			}
		}
		
		assertTrue(containsLastUpdate , "Attribute lastUpdate is missing");
	}

}
